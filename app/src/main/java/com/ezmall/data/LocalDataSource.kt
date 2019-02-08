package com.ezmall.data

import com.ezmall.R
import com.ezmall.models.*
import com.ezmall.ui.commons.MONTH_SHORT_NAME
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor() {

    private fun getOrder(): Order {

        val product = Product(
            "pid",
            "Test Product",
            123.12f,
            "M",
            "Blue",
            "https://images.voonik.com/78416852/embroidred-designer-navy-blue-and-yellow-color-geor-original.jpg?1512544999"
        )

        val calender = Calendar.getInstance()

        val ab = "${MONTH_SHORT_NAME[calender.time.month]} ${calender.time.date} ,${calender.time.year}"

        val user = User(
            "uid1",
            "Tejas Sherdiwala",
            "Some random land, Surat, Gujarat",
            "+91 9999999999"
        )

        return Order(
            "Pick any 1 Embrodered Suit by Zoha Crean",
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
            user,
            ab
        )
    }


    fun getOrders(): Single<List<Order>> {

        val order = mutableListOf<Order>()

        for (i in 0..5) {
            val ab = getOrder()
            when (i) {
                1 -> ab.currentOrderStatus = OrderStatus(OrderStatusEnum.CANCELLED, Date())
                2 -> ab.currentOrderStatus = OrderStatus(OrderStatusEnum.READY_TO_SHIP, Date())
                3 -> ab.currentOrderStatus = OrderStatus(OrderStatusEnum.DELIVERED, Date())
                4 -> ab.currentOrderStatus = OrderStatus(OrderStatusEnum.ORDERED, Date())
                5 -> ab.currentOrderStatus = OrderStatus(OrderStatusEnum.SHIPPED, Date())
            }
            order.add(ab)
        }

        return Single.just(order)
    }
}