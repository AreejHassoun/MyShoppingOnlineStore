package com.example.myshoppingonlinstore.utils

import com.example.myshoppingonlinstore.model.dataClasses.User
import com.example.myshoppingonlinstore.utils.enums.Language
import timber.log.Timber


object UserUtils {
    var currentUser: User? = null
    var currentLang: Language? = null


    fun getUserGender(userGender: Any?): Int {
        val genderStr = userGender.toString()
        val genderDouble = genderStr.toDouble()
        return try {
            val gender = genderDouble.toInt()
            gender
        } catch (e: NumberFormatException) {
            Timber.e(e, "Cannot parse string gender to integer")
            0
        }
    }
}