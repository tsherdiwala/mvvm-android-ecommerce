package com.ezmall.ui.orderlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.databinding.ItemOrderListBinding
import com.ezmall.models.Order
import com.ezmall.models.OrderStatusEnum

class OrderListAdapter(private var orderList: MutableList<Order>, private val viewModel: OrderListViewModel?) :
    RecyclerView.Adapter<OrderListAdapter.OrderListVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListVH {
        val view = DataBindingUtil.inflate<ItemOrderListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_order_list,
            parent,
            false
        )
        return OrderListVH(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderListVH, position: Int) {
        holder.bind(orderList[position])
    }

    fun update(item: MutableList<Order>) {
        this.orderList = item
        notifyDataSetChanged()
    }

    inner class OrderListVH(private val view: ItemOrderListBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(order: Order) {
            view.order = order
            if (view.order!!.currentOrderStatus.orderStatus.name == OrderStatusEnum.DELIVERED.name)
                view.orderListOpenIssueTV.visibility = View.VISIBLE
            view.executePendingBindings()
        }

    }
}