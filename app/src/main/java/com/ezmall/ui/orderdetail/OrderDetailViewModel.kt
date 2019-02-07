package com.ezmall.ui.orderdetail

import android.content.ContentValues.TAG
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezmall.data.Repository
import com.ezmall.models.Order
import io.reactivex.disposables.CompositeDisposable

class OrderDetailViewModel(private val repository: Repository) : ViewModel() {

    private val disposables = CompositeDisposable()

    var order = MutableLiveData<Order>()

    fun fetchOrders() {
        repository.fetchOrders()
            .subscribe(
                {
                    order.value = it
                },
                {
                    Log.e(TAG, "Unable to get order", it)
                }
            )
            ?.also {
                disposables.add(it)
            }

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}