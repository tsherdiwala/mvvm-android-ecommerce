package com.ezmall.models

data class Order(
    val title: String,
    val orderNumber: String,
    val subOrderNumber: String,
    var currentOrderStatus: OrderStatus,
    val orderStatusList: List<OrderStatus>,
    val products: Map<Product, Int>, // product and quantity
    val issues: List<Issue>?,
    val shippingCost: Float = 0f,
    val couponDiscounts: Float = 0f,
    val otherDiscounts: Float = 0f,
    val orderedBy: User,
    val orderDate: String
) {
    val productCost: Float
        get() {
            var price = 0f

            products.forEach {
                price += it.key.price * it.value
            }
            return price
        }

    val totalCost: Float
        get() {
            return productCost + shippingCost
        }

    val payable: Float
        get() = productCost + shippingCost - couponDiscounts - otherDiscounts

    val openIssueCount = issues?.filter { it.status == IssueStatus.OPEN }?.size ?: 0
}