package com.ezmall.models

data class Product(
    val id: String,
    val name: String,
    val price: Float,
    val size: String,
    val color: String,
    val imageUrl: String
)