package com.example.myshoppingonlinstore.utils.enums

import androidx.annotation.FontRes
import com.example.myshoppingonlinstore.R


/*
  Secondary Font Type
 // change sanserif font type(secondary font type)
 dependent on language

 */
enum class FontType (@FontRes val fontRes: Int) {
    SANSERIF_ENGLISH_REGULAR(R.font.sansserifflf),
    TAJWAL_ARABIC_REGULAR(R.font.tajawal_medium)
}