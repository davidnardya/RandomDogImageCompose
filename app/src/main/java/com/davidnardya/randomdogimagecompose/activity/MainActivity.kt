package com.davidnardya.randomdogimagecompose.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.rememberAsyncImagePainter
import com.davidnardya.randomdogimagecompose.model.Dog
import com.davidnardya.randomdogimagecompose.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetDog()
        }
    }

    @Composable
    fun GetDog() {

        val coroutineScope = rememberCoroutineScope()
        val (dog, setDog) = remember { mutableStateOf<Dog?>(null) }
        val getDogOnClick: () -> Unit = {
            coroutineScope.launch {
                setDog.invoke(viewModel.getDog())
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
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
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(dog?.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}