package com.ezmall.ui.main

import androidx.lifecycle.ViewModel
import com.ezmall.ui.commons.SingleLiveEvent

class MainViewModel: ViewModel(){

    val navigateOrderList = SingleLiveEvent<Void>()
    val navigateOrderDetail = SingleLiveEvent<Void>()

    fun openOrderList(){
        navigateOrderList.call()
    }

    fun openOrderDetails(){
        navigateOrderDetail.call()
    }

}