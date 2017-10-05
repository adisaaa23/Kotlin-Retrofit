package com.example.itpadmin.loginregisterkotlin.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ITP Admin on 09/08/2017.
 */

class ApiClient{

    companion object{
        val BASE_URL = "http://192.168.1.8/login_register/"

        var retrofit: Retrofit? = null

        private val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        private val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(interceptor)
                .build()
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
            }
            return retrofit
        }
    }


}