package com.ezmall.ui.orderdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.models.*

class OrderDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var order: Order? = null
        set(value) {
            field = value
            populateOrderDataList()
            notifyDataSetChanged()
        }

    private var orderDataList: List<Any>? = null

    private fun populateOrderDataList() {

        if (order == null) {
            return
        }

        val list = mutableListOf<Any>()

        list.add(OrderHeader(order!!.orderNumber, order!!.subOrderNumber))

        order!!.products.map {
            list.add(OrderProduct(it.key, it.value))
        }

        list.addAll(order!!.orderStatusList)

        order!!.issues?.let {
            list.add(
                OrderIssues(
                    order!!.openIssueCount,
                    it,
                    false
                )
            )
        }

        list.add(order!!.orderedBy)

        list.add(
            OrderCost(
                order!!.productCost,
                order!!.productCost,
                order!!.shippingCost,
                order!!.totalCost,
                order!!.couponDiscounts,
                order!!.otherDiscounts,
                order!!.payable
            )
        )

        list.add(
            OrderFooter()
        )
        orderDataList = list
    }


    override fun getItemViewType(position: Int): Int {
        return when (orderDataList?.get(position)) {
            is OrderHeader -> ORDER_HEADER
            is OrderProduct -> ORDER_PRODUCT
            is OrderStatus -> ORDER_STATUS
            is OrderIssues -> ORDER_ISSUE
            is User -> ORDER_USER
            is OrderCost -> ORDER_COST
            is OrderFooter -> ORDER_FOOTER
            else -> throw IllegalArgumentException("Unknown view for class : ${orderDataList?.get(position)?.javaClass?.simpleName}")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ORDER_HEADER -> {
                OrderHeaderVH()
            }
            ORDER_PRODUCT -> {
                OrderProductVH()
            }
            ORDER_STATUS -> {
                OrderStatusVH()
            }
            ORDER_ISSUE -> {
                OrderIssueVH()
            }
            ORDER_USER -> {
                OrderUserVH()
            }
            ORDER_COST -> {
                OrderCostVH()
            }
            ORDER_FOOTER -> {
                OrderFooterVH()
            }
            else -> throw IllegalArgumentException("Unsupported view type: $viewType")
        }
    }

    override fun getItemCount(): Int {
        return orderDataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }


    inner class OrderHeaderVH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrderHeader) {

        }
    }

    inner class OrderProductVH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrderProduct) {

        }
    }

    inner class OrderStatusVH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrderStatus) {

        }
    }

    inner class OrderIssueVH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrderHeader) {

        }
    }

    inner class OrderUserVH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: User) {

        }
    }

    inner class OrderCostVH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrderCost) {

        }
    }

    inner class OrderFooterVH(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrderFooter) {

        }
    }


    private data class OrderHeader(val orderNumber: String, val subOrderNumber: String)

    private data class OrderProduct(val product: Product, val quantity: Int)

    private data class OrderIssues(val openIssueCount: Int, val issues: List<Issue>, var isExpanded: Boolean)

    private data class OrderCost(
        val listPrice: Float,
        val sellingPrice: Float,
        val shippingCharges: Float,
        val total: Float,
        val couponDiscount: Float,
        val otherDiscount: Float,
        val payable: Float
    )

    private class OrderFooter

    companion object {
        private const val ORDER_HEADER = 1
        private const val ORDER_PRODUCT = 2
        private const val ORDER_STATUS = 3
        private const val ORDER_ISSUE = 4
        private const val ORDER_USER = 5
        private const val ORDER_COST = 6
        private const val ORDER_FOOTER = 7
    }
}