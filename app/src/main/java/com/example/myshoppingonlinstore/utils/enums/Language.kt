package com.example.myshoppingonlinstore.utils.enums

import com.example.myshoppingonlinstore.R


/**
 * Language
 *
 */
enum class Language(val id: Long, val value: String, val nameId: Int) {
    AR(1, "ar", R.string.ar_lang),
    EN(2, "en", R.string.en_lang)
}