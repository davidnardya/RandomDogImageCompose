package com.davidnardya.randomdogimagecompose.api

import com.davidnardya.randomdogimagecompose.utils.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun <T> create(service: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build()
        return retrofit.create(service)
    }

}