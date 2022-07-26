package com.davidnardya.randomdogimagecompose.viewmodel

import androidx.lifecycle.ViewModel
import com.davidnardya.randomdogimagecompose.model.Dog
import com.davidnardya.randomdogimagecompose.repositories.DogRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dogRepository: DogRepository
): ViewModel() {
    private suspend fun getDog() = dogRepository.getDog()

    val getDogListFlow = flow<List<Dog>> {
        val dogList = mutableListOf<Dog>()
        for(i in 1..10) {
            dogList.add(getDog())
        }
        emit(dogList)
    }
}