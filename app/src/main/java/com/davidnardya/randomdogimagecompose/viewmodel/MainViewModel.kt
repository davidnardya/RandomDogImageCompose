package com.davidnardya.randomdogimagecompose.viewmodel

import androidx.lifecycle.ViewModel
import com.davidnardya.randomdogimagecompose.model.Dog
import com.davidnardya.randomdogimagecompose.repositories.DogRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dogRepository: DogRepository
): ViewModel() {
    suspend fun getDog() = dogRepository.getDog()
}