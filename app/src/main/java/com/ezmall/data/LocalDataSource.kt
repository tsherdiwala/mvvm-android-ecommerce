package com.ezmall.data

import com.ezmall.models.*
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor() {
    fun getOrders(): Single<Order> {

        val product = Product(
            "pid",
            "Test Product",
            123.12f,
            "M",
            "Blue",
            "https://images.voonik.com/78416852/embroidred-designer-navy-blue-and-yellow-color-geor-original.jpg?1512544999",
            Date(),
            true
        )
        val issueList = mutableListOf<Issue>()
        val user = User(
            "uid1",
            "Tejas Sherdiwala",
            "Some random land, Surat, Gujarat",
            "+91 9999999999"
        )
        val issue = Issue(
            "3210",
            "product Demage",
            Date(),
            IssueStatus.OPEN
        )
        val issue1 = Issue(
            "3211",
            "Product Delivery Related",
            Date(),
            IssueStatus.CLOSED
        )
        val issue2 = Issue(
            "3212",
            "Delivery Related",
            Date(),
            IssueStatus.CLOSED
        )
        val issue4 = Issue(
            "3214",
            "Dispatch Late",
            Date(),
            IssueStatus.CLOSED
        )
        val issue5 = Issue(
            "3215",
            "Product Dispatch Late",
            Date(),
            IssueStatus.CLOSED
        )
        val issue3 = Issue(
            "3213",
            "Product Related",
            Date(),
            IssueStatus.OPEN
        )

        issueList.add(issue)
        issueList.add(issue1)
        issueList.add(issue2)
        issueList.add(issue3)
        issueList.add(issue4)
        issueList.add(issue5)


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
            issueList,
            10.12f,
            1.1f,
            2.0f,
            user
        )
        return Single.just(order)
    }
}