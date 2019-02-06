package com.ezmall.ui.commons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ezmall.data.Repository
import com.ezmall.ui.orderlist.OrderListViewModel
import javax.inject.Inject

class AppViewModelFactory @Inject constructor(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(OrderListViewModel::class.java) -> OrderListViewModel(repository) as T
            else -> throw IllegalArgumentException("Unsupported model class: ${modelClass.simpleName}")
        }
    }
}