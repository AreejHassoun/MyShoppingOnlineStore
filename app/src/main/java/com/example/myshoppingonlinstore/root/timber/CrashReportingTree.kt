package com.example.myshoppingonlinstore.root.timber

import com.example.myshoppingonlinstore.BuildConfig
import com.example.myshoppingonlinstore.utils.isCancellationException
import com.example.myshoppingonlinstore.utils.isNetworkThrowable
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber


class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when {
            BuildConfig.enableFirebaseLogging -> {
                // Log tag and message to Firebase for better understanding of error.
                FirebaseCrashlytics.getInstance().log(
                    "***START OF LOG***\n\n${tag ?: "NO_TAG"} --> ${message}.\n\n***END OF LOG***"
                )

                // Log only non network and non cancellation throwable.
                t?.let {
                    if (!isNetworkThrowable(t) && !isCancellationException(t as Exception)) {
                        FirebaseCrashlytics.getInstance().recordException(t)
                    }
                }
            }
        }
    }
}