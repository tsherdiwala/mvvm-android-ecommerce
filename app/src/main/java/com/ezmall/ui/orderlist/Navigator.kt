package com.ezmall.ui.orderlist

import com.ezmall.models.Order

interface Navigator {

    fun onSuccess(list: List<Order>)

    fun onError(it: Throwable)
}