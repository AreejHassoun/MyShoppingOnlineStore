package com.example.myshoppingonlinstore.model.dataClasses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id")
    var id:Long?=null,
    @Json(name = "first_name")
    var firstName:String?=null,
    @Json(name = "last_name")
    var lastName:String?=null,
    @Json(name = "photo")
    var  imageUrl:String?=null,
    @Json(name = "email")
    var email:String?=null,
    @Json(name = "mobile")
    var mobile: String? = null,
    @Json(name = "prefix")
    var prefix: String? = null
)