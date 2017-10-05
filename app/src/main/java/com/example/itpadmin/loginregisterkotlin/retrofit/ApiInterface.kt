package com.example.itpadmin.loginregisterkotlin.retrofit

import com.example.itpadmin.loginregisterkotlin.model.ResponseApi
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by ITP Admin on 09/08/2017.
 */

interface ApiInterface{
    @FormUrlEncoded
    @POST("login.php")
    abstract fun login(
            @Field("email") email: String,
            @Field("password") password: String): Call<ResponseApi>

    @FormUrlEncoded
    @POST("register.php")
    abstract fun register(
            @Field("name") name: String,
            @Field("email") email: String,
            @Field("password") password: String,
            @Field("telp") telp: String): Call<ResponseApi>
}