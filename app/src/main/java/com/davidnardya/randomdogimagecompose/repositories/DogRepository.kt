package com.davidnardya.randomdogimagecompose.repositories

import com.davidnardya.randomdogimagecompose.model.Dog
import com.davidnardya.randomdogimagecompose.api.DogApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogApi: DogApi
) {
    // observe repository instead
    private val dogs = MutableStateFlow(emptyList<Dog>())

    private suspend fun getDog() = dogApi.getDog()

    fun getDogsFlow(): Flow<List<Dog>> = dogs

    suspend fun loadPage(page: Long) {
        val dogs = mutableListOf<Dog>()
        for(i in 1..10) {
            dogs.add(getDog())
        }
//        val old = this.dogs.value.toMutableList()
//        old.addAll(dogs)
        this.dogs.tryEmit(dogs)
    }
}
