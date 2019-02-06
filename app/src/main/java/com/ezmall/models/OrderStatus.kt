package com.ezmall.models

import java.util.*

data class OrderStatus(val orderStatus: OrderStatusEnum, val date: Date, val isCompleted: Boolean = true)