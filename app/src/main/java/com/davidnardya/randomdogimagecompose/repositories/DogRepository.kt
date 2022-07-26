package com.davidnardya.randomdogimagecompose.repositories

import com.davidnardya.randomdogimagecompose.model.Dog
import com.davidnardya.randomdogimagecompose.api.DogApi
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogApi: DogApi
) {
    suspend fun getDog() = dogApi.getDog()
}