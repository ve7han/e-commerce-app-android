package com.example.a3e_commerce.model

import com.google.gson.annotations.SerializedName

data class Product(

    val title : String,
    @SerializedName("photo_url")
    val photoUrl : String,
    val price : Double
    )
