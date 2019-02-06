package com.ezmall.ui.orderlist

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ezmall.data.Repository
import com.ezmall.models.Order
import com.ezmall.ui.commons.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

private val TAG = OrderListViewModel::class.java.simpleName

class OrderListViewModel(private val repository: Repository) : ViewModel() {

    private val disposables = CompositeDisposable()

    val navigateFetchOrderListSuccess = SingleLiveEvent<List<Order>>()
    val navigateFetchOrderListError = SingleLiveEvent<Throwable>()


    fun fetchOrders() {
        repository.fetchOrders()
            ?.subscribe(
                {
                    Log.e(TAG, "$it")
                    navigateFetchOrderListSuccess.value = it
                },
                {
                    Log.e(TAG, "Unable to get order", it)
                    navigateFetchOrderListError.value = it
                }
            )
            ?.also {
                disposables.add(it)
            }

    }

}