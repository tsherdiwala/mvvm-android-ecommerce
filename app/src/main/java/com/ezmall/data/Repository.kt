package com.ezmall.data

import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: LocalDataSource) {

    fun fetchOrders() = localDataSource.getOrders()
}