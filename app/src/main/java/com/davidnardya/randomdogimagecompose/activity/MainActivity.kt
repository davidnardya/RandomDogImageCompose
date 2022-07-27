package com.davidnardya.randomdogimagecompose.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.davidnardya.randomdogimagecompose.model.Dog
import com.davidnardya.randomdogimagecompose.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getDogsFlow().onEach { setupViews(it) }.launchIn(lifecycleScope)
        viewModel.onCreate()
        viewModel.doSomething()
    }

    private fun setupViews(dogs: List<Dog>) {
        setContent {
            GetDog(dogs)
            FloatingActionButton(
                onClick = { viewModel.doSomething() }
            ) {}
        }
    }


    @Composable
    fun ShowDogOnScreen(dog: Dog) {
        Row {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(dog.image)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    @Composable
    fun GetDog(dogList: List<Dog>) {
        LazyColumn {
            items(dogList) { dog ->
                ShowDogOnScreen(dog)
            }
        }
    }
}