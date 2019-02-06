package com.ezmall.data

import com.ezmall.models.*
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor() {
    fun getOrders(): Single<List<Order>> {
        val product = Product("pid", "Test Product", 123.12f, "M", "Blue")

        val user = User(
            "uid1",
            "Tejas Sherdiwala",
            "Some random land, Surat, Gujarat",
            "+91 9999999999"
        )

        val order = Order(
            "12345",
            "12345",
            OrderStatus(OrderStatusEnum.ORDERED, Date()),
            listOf(
                OrderStatus(OrderStatusEnum.ORDERED, Date()),
                OrderStatus(OrderStatusEnum.READY_TO_SHIP, Date(), false),
                OrderStatus(OrderStatusEnum.SHIPPED, Date(), false),
                OrderStatus(OrderStatusEnum.DELIVERED, Date(), false)
            ),
            mapOf(
                product to 2
            ),
            null,
            10.12f,
            1.1f,
            2.0f,
            user
        )
        return Single.just(listOf(order))
    }
}