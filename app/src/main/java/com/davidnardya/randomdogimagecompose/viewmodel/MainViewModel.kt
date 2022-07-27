package com.davidnardya.randomdogimagecompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidnardya.randomdogimagecompose.model.Dog
import com.davidnardya.randomdogimagecompose.repositories.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    private val pages = MutableSharedFlow<Long>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun getDogsFlow(): Flow<List<Dog>> = dogRepository.getDogsFlow()

    fun doSomething() {
        pages.tryEmit(System.currentTimeMillis())
    }

    fun onCreate() {
        pages.onEach {
            dogRepository.loadPage(it)
        }
            .catch {
                it.printStackTrace()
                onCreate()
                // TODO: show error msg
            }
            .launchIn(viewModelScope)
    }
}