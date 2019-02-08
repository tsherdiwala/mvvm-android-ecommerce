package com.ezmall.data

import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: LocalDataSource) {

    fun fetchOrder() = localDataSource.getOrder()

    fun fetchOrders() = localDataSource.getOrders()
}