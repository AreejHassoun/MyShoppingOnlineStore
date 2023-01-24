package com.example.myshoppingonlinstore.utils

import android.provider.ContactsContract.Directory.PACKAGE_NAME
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.myshoppingonlinstore.BuildConfig


/**
 * PreferencesUtils
 */

const val DATA_STORE_NAME = "${BuildConfig.PACKAGE_NAME}.MyShopDataStore"

object PreferenceKeys {
    val KEY_LANG = stringPreferencesKey("${BuildConfig.PACKAGE_NAME}.Lang")
    val KEY_ON_NOTIFICATION_SHOWN = booleanPreferencesKey("${BuildConfig.PACKAGE_NAME}.OnNotificationShown")
    val KEY_USER = stringPreferencesKey("${BuildConfig.PACKAGE_NAME}.User")
    val KEY_ON_BOARDING_SHOWN = booleanPreferencesKey("${BuildConfig.PACKAGE_NAME}.OnBoardingShown")
    val KEY_GUEST_TOKEN = stringPreferencesKey("${BuildConfig.PACKAGE_NAME}.GuestToken")
    val KEY_ON_BASKET_ID = intPreferencesKey("${BuildConfig.PACKAGE_NAME}.BasketId")
    val KEY_CURRENT_SERVICES = stringPreferencesKey("${BuildConfig.PACKAGE_NAME}.CurrentService")
    val KEY_CART_BADGE = intPreferencesKey("${BuildConfig.PACKAGE_NAME}.CartBadge")
    val KEY_SERVICE_ID= intPreferencesKey("${BuildConfig.PACKAGE_NAME}.ServiceId")

}