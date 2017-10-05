package com.example.itpadmin.loginregisterkotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ITP Admin on 09/08/2017.
 */

data class ResponseApi (
    var error: Boolean,
    var message: String,
    var data: User
)
