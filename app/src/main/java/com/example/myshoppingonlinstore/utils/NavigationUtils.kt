package com.example.myshoppingonlinstore.utils

import androidx.navigation.NavOptions
import com.example.myshoppingonlinstore.R

/**
 * NavigationUtils
 */

//region Navigation options
val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

val navOptionsHomeBack = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .setPopUpTo(R.id.homeFragment, false)
    .build()

val navOptionsLoginBack = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
//    .setPopUpTo(R.id.loginFragment, false)
    .build()

val navOptionsNoBack = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .setPopUpTo(R.id.nav_graph, true)
    .setLaunchSingleTop(true)
    .build()

//endregion
const val NAV_KEY_OPEN_NAV_DRAWER = "openNavDrawer"
const val NAV_KEY_SAVE_BADGET = "saveBadgetNotification"
const val NAV_KEY_GIFT_REGISTERY = "giftRegistery"
const val NAV_KEY_ORDER_ITEM="orderItem"
const val NAV_KEY_SPLIT_PAYMENT="splitPay"
const val NAV_KEY_OPEN_SELECTED_COUNTRY = "selectedCountry"
//region Navigation keys

//endregion