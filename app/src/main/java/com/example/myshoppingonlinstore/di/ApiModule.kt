package com.example.myshoppingonlinstore.di

import androidx.paging.ExperimentalPagingApi
import com.example.myshoppingonlinstore.BuildConfig
import com.example.myshoppingonlinstore.api.ApiRepo
import com.example.myshoppingonlinstore.api.ApiRepository
import com.example.myshoppingonlinstore.api.ApiService
import com.example.myshoppingonlinstore.utils.DEFAULT_LANG
import com.example.myshoppingonlinstore.utils.UserUtils
import com.example.myshoppingonlinstore.utils.isNetworkAvailable
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import kotlin.time.ExperimentalTime
import kotlin.time.days

@OptIn(KoinApiExtension::class)
@ExperimentalTime
@ExperimentalPagingApi
val apiModule = module {
    // Provide custom instance of [HttpLoggingInterceptor] to be attached to Retrofit for logging network calls.
    single {
        HttpLoggingInterceptor().apply {
            level = when (BuildConfig.enableDebugLogging) {
                true -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }


    // Provide [Interceptor] for adding headers for all requests.
    single(named("HeadersInterceptor")) {
        Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Content-Type", "application/json")
            request.addHeader("X-App-Client", "android")


            val userLanguage = UserUtils.currentLang?.value ?: DEFAULT_LANG.value
            request.addHeader("Accept-Language", userLanguage)

            //TODO add user utils to get api token
            return@Interceptor chain.proceed(request.build())
        }
    }

    // Provide [Cache] to be used in caching network calls.
    single {
        val cacheDirectory = File(androidContext().cacheDir, "MyShopResponses")
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB
        Cache(cacheDirectory, cacheSize)
    }

    // Provide [Interceptor] for adding online and offline cache to network requests.
    single(named("NetworkInterceptor")) {
        Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())

            return@Interceptor originalResponse.newBuilder()
                .header(
                    "Cache-Control",
                    "public, max-age=${
                        if (isNetworkAvailable(androidContext()))
                            0
                        else
                            28.days.inSeconds.toLong()
                    }"
                )
                .build()
        }
    }

    // Provide custom instance of [OkHttpClient].
    single {
        val httpLoggingInterceptor: HttpLoggingInterceptor by inject()
        val headersInterceptor: Interceptor by inject(named("HeadersInterceptor"))
        val cache: Cache by inject()
        val networkInterceptor: Interceptor by inject(named("NetworkInterceptor"))

        val builder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .hostnameVerifier(HostnameVerifier { hostname, session ->
                val hv = HttpsURLConnection.getDefaultHostnameVerifier()

                return@HostnameVerifier when (hostname) {
                    BuildConfig.BASE_URL
                        .replace("https://", "")
                        .replace("http://", "") -> true

                    else -> hv.verify(hostname, session)
                }
            })

        builder
            .addInterceptor(headersInterceptor)
            .cache(cache)
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(httpLoggingInterceptor)

        builder.build()
    }

    // Provide instance of [NetworkResponseAdapterFactory].
    single {
        NetworkResponseAdapterFactory()
    }

    // Provide instance of [MoshiConverterFactory].
    single {
        MoshiConverterFactory.create()
    }

    // Provide instance of [ApiService].
    single {
        val client: OkHttpClient by inject()
        val networkResponseAdapterFactory: NetworkResponseAdapterFactory by inject()
        val moshiConverterFactory: MoshiConverterFactory by inject()

        Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}/api/v1/")
            .client(client)
            .addCallAdapterFactory(networkResponseAdapterFactory)
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(ApiService::class.java)
    }

    // Provide instance of [ApiRepo].

    single<ApiRepo> {
        ApiRepository(get())
    }

    // Provide instance of [Moshi] with date adapter.
    single {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
}


