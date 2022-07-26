package com.davidnardya.randomdogimagecompose.model

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("message")
    val image: String,
    @SerializedName("status")
    val status: String
)