package com.davidnardya.randomdogimagecompose.api

import com.davidnardya.randomdogimagecompose.model.Dog
import retrofit2.http.GET

interface DogApi {
    @GET("breeds/image/random")
    suspend fun getDog(): Dog
}