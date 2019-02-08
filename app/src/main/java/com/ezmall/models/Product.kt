package com.ezmall.models

import java.util.*

data class Product(
    val id: String,
    val name: String,
    val price: Float,
    val size: String,
    val color: String,
    val imageUrl: String,
    val returnDate: Date,
    val isCashOnDeliveryAvailable: Boolean
)