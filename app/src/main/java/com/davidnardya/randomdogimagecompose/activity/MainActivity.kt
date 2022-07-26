package com.davidnardya.randomdogimagecompose.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.davidnardya.randomdogimagecompose.model.Dog
import com.davidnardya.randomdogimagecompose.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private val initDogList = mutableListOf<Dog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            //CAN USE THIS
//            val dogList by viewModel.getDogListFlow.collectAsState(initial = initDogList)
            //OR THIS
//            val dogList = viewModel.getDogListFlow.collectAsState(initial = initDogList).value
//            GetDog(dogList = dogList)
            //BOTH WORK JUST FINE

            GetDog(dogList = viewModel.getDogListFlow.collectAsState(initial = initDogList).value)
        }
    }

    @Composable
    fun ShowDogOnScreen(dog: Dog) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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

        /*val coroutineScope = rememberCoroutineScope()
        val (dog, setDog) = remember { mutableStateOf<Dog?>(null) }
        val getDogOnClick: () -> Unit = {
            coroutineScope.launch {
                setDog.invoke(viewModel.getDog())
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = getDogOnClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Gray
                )
            ) {
                Text(text = "Click to Get Dog")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = rememberAsyncImagePainter(dog?.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }*/
    }
}