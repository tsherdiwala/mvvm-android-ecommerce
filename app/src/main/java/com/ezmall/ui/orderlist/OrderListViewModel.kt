package com.ezmall.ui.orderlist

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ezmall.data.Repository
import io.reactivex.disposables.CompositeDisposable

private val TAG = OrderListViewModel::class.java.simpleName

class OrderListViewModel(private val repository: Repository) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun fetchOrders() {
        repository.fetchOrders()
            .subscribe(
                {
                    Log.d(TAG, "$it")
                },
                {
                    Log.e(TAG, "Unable to get order", it)
                }
            ).also {
                disposables.add(it)
            }

    }

}