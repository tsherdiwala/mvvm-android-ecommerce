package com.ezmall.ui.orderlist

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.ezmall.data.Repository
import com.ezmall.models.Order
import com.ezmall.ui.commons.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

private val TAG = OrderListViewModel::class.java.simpleName

class OrderListViewModel(private val repository: Repository) : ViewModel() {

    private val disposables = CompositeDisposable()

    val orders = ObservableArrayList<Order>()

    fun fetchOrders() {
        repository.fetchOrders()
            .subscribe(
                {
                    this.orders.addAll(it)
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