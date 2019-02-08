package com.ezmall.ui.orderdetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.databinding.*
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

        list.add(OrderView())

        order!!.products.map {
            list.add(OrderProduct(it.key, it.value))
        }

        list.add(OrderView())

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

        list.add(OrderView())

        list.add(order!!.orderedBy)

        list.add(OrderView())

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
            is OrderView -> ORDER_EMPTY_VIEW
            else -> throw IllegalArgumentException("Unknown view for class : ${orderDataList?.get(position)?.javaClass?.simpleName}")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ORDER_HEADER -> {
                val binding = DataBindingUtil.inflate<ItemOrderDetailHeaderBinding>(
                    inflater,
                    R.layout.item_order_detail_header,
                    parent,
                    false
                )
                OrderHeaderVH(binding)
            }
            ORDER_PRODUCT -> {
                val binding = DataBindingUtil.inflate<ItemOrderDetailProductBinding>(
                    inflater,
                    R.layout.item_order_detail_product,
                    parent,
                    false
                )
                OrderProductVH(binding)
            }
            ORDER_STATUS -> {
                val binding = DataBindingUtil.inflate<ItemOrderDetailStatusBinding>(
                    inflater,
                    R.layout.item_order_detail_status,
                    parent,
                    false
                )
                OrderStatusVH(binding)
            }
            ORDER_ISSUE -> {
                val binding = DataBindingUtil.inflate<ItemOrderDetailIssueBinding>(
                    inflater,
                    R.layout.item_order_detail_issue,
                    parent,
                    false
                )
                OrderIssueVH(binding)
            }
            ORDER_USER -> {
                val binding = DataBindingUtil.inflate<ItemOrderDetailUserBinding>(
                    inflater,
                    R.layout.item_order_detail_user,
                    parent,
                    false
                )
                OrderUserVH(binding)
            }
            ORDER_COST -> {
                val binding = DataBindingUtil.inflate<ItemOrderDetailCostBinding>(
                    inflater,
                    R.layout.item_order_detail_cost,
                    parent,
                    false
                )
                OrderCostVH(binding)
            }
            ORDER_FOOTER -> {
                val view = inflater.inflate(R.layout.item_order_detail_footer, parent, false)
                OrderFooterVH(view)
            }
            ORDER_EMPTY_VIEW -> {
                val view = inflater.inflate(R.layout.item_order_detail_view, parent, false)
                OrderViewVH(view)
            }
            else -> throw IllegalArgumentException("Unsupported view type: $viewType")
        }
    }

    override fun getItemCount(): Int {
        return orderDataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is OrderHeaderVH -> holder.bind(orderDataList?.get(position) as OrderHeader)
            is OrderProductVH -> holder.bind(orderDataList?.get(position) as OrderProduct)
            is OrderStatusVH -> holder.bind(orderDataList?.get(position) as OrderStatus)
            is OrderIssueVH -> holder.bind(orderDataList?.get(position) as OrderIssues)
            is OrderUserVH -> holder.bind(orderDataList?.get(position) as User)
            is OrderCostVH -> holder.bind(orderDataList?.get(position) as OrderCost)
            is OrderFooterVH -> holder.bind(orderDataList?.get(position) as OrderFooter)
        }
    }


    inner class OrderHeaderVH(private val binding: ItemOrderDetailHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(orderHeader: OrderHeader) {
            binding.orderHeader = orderHeader
            binding.executePendingBindings()
        }

    }

    inner class OrderProductVH(private val binding: ItemOrderDetailProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(orderProduct: OrderProduct) {
            binding.product = orderProduct
            binding.executePendingBindings()

        }

    }

    inner class OrderStatusVH(private val binding: ItemOrderDetailStatusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderStatus: OrderStatus) {

            with(binding) {
                this.orderStatus = orderStatus
                isTopLineVisible = (orderDataList?.get(adapterPosition - 1) is OrderStatus)
                isBottomLineVisible = (orderDataList?.get(adapterPosition + 1) is OrderStatus)
            }
            binding.executePendingBindings()
        }

    }

    inner class OrderIssueVH(private val binding: ItemOrderDetailIssueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(orderIssues: OrderIssues) {
            binding.issue = orderIssues
            binding.executePendingBindings()

            val orderIssueChildLL = binding.orderDetailChildLL
            val layoutInflater = LayoutInflater.from(orderIssueChildLL.context)

            orderIssueChildLL.removeAllViews()

            if (orderIssues.isExpanded) {

                orderIssues.issues.forEach {
                    val itemIssue = DataBindingUtil.inflate<ItemOrderDetailIssueItemBinding>(
                        layoutInflater,
                        R.layout.item_order_detail_issue_item,
                        orderIssueChildLL,
                        false
                    )
                    orderIssueChildLL.addView(itemIssue.root)
                    itemIssue.issueItem = it
                }

            }


            binding.orderDetailIssueLL.setOnClickListener {
                orderIssues.isExpanded = !orderIssues.isExpanded
                notifyItemChanged(adapterPosition)

            }

        }
    }

    inner class OrderUserVH(private val binding: ItemOrderDetailUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }

    }

    inner class OrderCostVH(private val binding: ItemOrderDetailCostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(orderCost: OrderCost) {
            binding.orderCost = orderCost
            binding.executePendingBindings()
        }

    }

    inner class OrderFooterVH(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(orderFooter: OrderFooter) {

        }

    }

    inner class OrderViewVH(view: View) : RecyclerView.ViewHolder(view) {

    }


    data class OrderHeader(val orderNumber: String, val subOrderNumber: String)

    data class OrderProduct(val product: Product, val quantity: Int)

    data class OrderIssues(val openIssueCount: Int, val issues: List<Issue>, var isExpanded: Boolean)

    data class OrderCost(
        val listPrice: Float,
        val sellingPrice: Float,
        val shippingCharges: Float,
        val total: Float,
        val couponDiscount: Float,
        val otherDiscount: Float,
        val payable: Float
    )

    class OrderFooter

    class OrderView

    companion object {
        private const val ORDER_HEADER = 1
        private const val ORDER_PRODUCT = 2
        private const val ORDER_STATUS = 3
        private const val ORDER_ISSUE = 4
        private const val ORDER_USER = 5
        private const val ORDER_COST = 6
        private const val ORDER_FOOTER = 7
        private const val ORDER_EMPTY_VIEW = 8
    }
}