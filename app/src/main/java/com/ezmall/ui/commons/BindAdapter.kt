package com.ezmall.ui.commons

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.extension.getParentActivity
import com.ezmall.models.Order
import com.ezmall.models.OrderStatus
import com.ezmall.models.OrderStatusEnum
import com.ezmall.models.Product
import com.ezmall.ui.orderlist.OrderListAdapter


@BindingAdapter("orderList")
fun setOrderList(recyclerView: RecyclerView, item: MutableList<Order>) {

    with(recyclerView.adapter as OrderListAdapter) {
        update(item)
    }
}

@BindingAdapter("mutableProductText")
fun setMutableProductText(view: TextView, text: Map<Product, Int>?) {

    val parentActivity = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.forEach {
            view.text = it.key.name
        }
    }
}

@BindingAdapter("mutableOrderDate")
fun setMutableOrderDate(view: TextView, text: String?) {
    val parentActivity = view.getParentActivity()
    if (parentActivity != null && text != null) {
        view.text = parentActivity.getString(R.string.order_date, text)
    }
}

@BindingAdapter("mutableImageUrl")
fun setMutableImageUrl(view: ImageView, map: Map<Product, Int>?) {

    val parentActivity = view.getParentActivity()
    if (parentActivity != null && map != null) {
        map.forEach {
            Log.e("TAG", it.key.imageUrl)
            GlideApp.with(view)
                .load(it.key.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view)
        }

    }
}

@BindingAdapter("mutableTextStatus")
fun setMutableTextStatus(view: TextView, text: OrderStatus?) {

    val parentActivity = view.getParentActivity()
    if (parentActivity != null && text != null) {
        when {
            text.orderStatus.name == OrderStatusEnum.ORDERED.name -> {
                view.text = parentActivity.getString(R.string.order_Status_ordered)
                view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_red_dark))
            }
            text.orderStatus.name == OrderStatusEnum.READY_TO_SHIP.name -> {
                view.text = parentActivity.getString(R.string.order_Status_Ready_to_ship)
                view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_blue_dark))
            }
            text.orderStatus.name == OrderStatusEnum.CANCELLED.name -> {
                view.text = parentActivity.getString(R.string.order_Status_Cancle)
                view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_green_dark))
            }
            text.orderStatus.name == OrderStatusEnum.SHIPPED.name -> {
                view.text = parentActivity.getString(R.string.order_Status_Shipped)
                view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_orange_light))
            }
            text.orderStatus.name == OrderStatusEnum.DELIVERED.name -> {
                view.text = parentActivity.getString(R.string.order_Status_Delivered)
                view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_purple))
            }
        }
    }
}

@BindingAdapter("mutableTextFloat")
fun setMutableTextFloat(view: TextView, text: Float?) {

    val parentActivity = view.getParentActivity()
    if (parentActivity != null && text != null)
        view.text = text.toString() ?: ""
}