package com.example.myshoppingonlinstore.root

import androidx.multidex.MultiDexApplication
import androidx.paging.ExperimentalPagingApi
import com.example.myshoppingonlinstore.BuildConfig
import com.example.myshoppingonlinstore.di.apiModule
import com.example.myshoppingonlinstore.di.modelModule
import com.example.myshoppingonlinstore.di.preferencesModule
import com.example.myshoppingonlinstore.di.viewModelsModule
import com.example.myshoppingonlinstore.root.timber.CrashReportingTree
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber
import kotlin.time.ExperimentalTime

@Suppress("unused")
@ExperimentalTime
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin.
        initKoin()
        // Initialize libraries.
        initLibraries()
    }
    //endregion



    //region Private methods
    /**
     * Initialize dependency injection using Koin.
     */
    private fun initKoin() {
        startKoin {
            // Use the Android context given there.
            androidContext(this@App)

            // Load properties from assets/koin.properties file.
            androidFileProperties()

            // Module list.
            modules(getKoinModules())
        }
    }

    /**
     * Get a list of dependency injection used in application.
     */

    @OptIn(ExperimentalPagingApi::class)
    private fun getKoinModules(): List<Module> {
        return listOf(
            apiModule,
            preferencesModule,
            modelModule,
            viewModelsModule
        )
    }

    /**
     * Initialize libraries used in the application.
     */
    private fun initLibraries() {
        initFirebase()
        initTimber()
    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }

    /**
     * Initialize Timber.
     * For "DEBUG" plant a "DebugTree" for logging in Logcat.
     * For "RELEASE" plant a "CrashReportingTree" for logging in Fabric.
     */
    private fun initTimber() {
        when (BuildConfig.enableDebugLogging) {
            true -> Timber.plant(Timber.DebugTree())
            else -> Timber.plant(CrashReportingTree())
        }
    }



    //endregion

}