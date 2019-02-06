package com.ezmall.ui.orderlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezmall.models.*

class OrderViewModel : ViewModel() {

    private val orderNo = MutableLiveData<String>()
    private val subOrderNo = MutableLiveData<String>()
    private val currentOrderStatus = MutableLiveData<String>()
    private val orderStatusList = MutableLiveData<List<OrderStatus>>()
    private val product = MutableLiveData<Map<Product, Int>>()
    private val issue = MutableLiveData<List<Issue>>()
    private val shippingCost = MutableLiveData<Float>()
    private val couponDiscounts = MutableLiveData<Float>()
    private val otherDiscounts = MutableLiveData<Float>()
    private val orderBy = MutableLiveData<User>()

    private val productPrice = MutableLiveData<Float>()
    private val orderDate = MutableLiveData<String>()


    fun bind(order: Order) {
        orderNo.value = order.orderNumber
        subOrderNo.value = order.subOrderNumber
        currentOrderStatus.value = order.currentOrderStatus.orderStatus.toString()
        orderStatusList.value = order.orderStatusList
        issue.value = order.issues
        shippingCost.value = order.shippingCost
        couponDiscounts.value = order.couponDiscounts
        otherDiscounts.value = order.otherDiscounts
        orderBy.value = order.orderedBy

        product.value = order.products
        productPrice.value = order.productCost
        orderDate.value = order.orderDate
    }

    fun getProductName(): MutableLiveData<Map<Product, Int>> {
        return product
    }

    fun getProductPrice(): MutableLiveData<Float> {
        return productPrice
    }

    fun getOrderDate(): MutableLiveData<String> {
        return orderDate
    }

    fun getCurrentOrderStatus(): MutableLiveData<String> {
        return currentOrderStatus
    }
}