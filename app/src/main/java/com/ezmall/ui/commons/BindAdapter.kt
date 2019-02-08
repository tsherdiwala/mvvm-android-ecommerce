package com.ezmall.ui.commons

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.extension.getParentActivity
import com.ezmall.models.Order
import com.ezmall.models.OrderStatusEnum
import com.ezmall.models.Product
import com.ezmall.ui.orderlist.OrderListAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("orderList")
fun setOrderList(recyclerView: RecyclerView, item: MutableList<Order>) {

    with(recyclerView.adapter as OrderListAdapter) {
        update(item)
    }
}

@BindingAdapter(value = ["date", "dateFormat", "prefix"], requireAll = false)
fun setDate(view: TextView, date: Date?, format: String?, prefix: Int?) {
    val parentActivity = view.getParentActivity()
    if (parentActivity != null) {


        (format ?: "dd/MM/yy").run {
            SimpleDateFormat(this, Locale.ENGLISH).run {

                if (prefix != null) {
                    view.text = parentActivity.getString(prefix, format(date))
                } else {
                    view.text = format(date)
                }
            }
        }
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

@BindingAdapter("orderStatus")
fun setMutableTextStatus(view: TextView, orderStatus: OrderStatusEnum?) {

    val parentActivity = view.getParentActivity()
    if (parentActivity != null && orderStatus != null) {

        when (orderStatus) {
            OrderStatusEnum.ORDERED -> {
                view.text = parentActivity.getString(R.string.order_status_ordered)
                view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_red_dark))
            }
            OrderStatusEnum.READY_TO_SHIP -> {
                view.text = parentActivity.getString(R.string.order_status_ready_to_ship)
                view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_blue_dark))
            }
            OrderStatusEnum.CANCELLED -> {
                view.text = parentActivity.getString(R.string.order_status_cancelled)
                view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_green_dark))
            }
            OrderStatusEnum.SHIPPED -> {
                view.text = parentActivity.getString(R.string.order_Status_shipped)
                view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_orange_light))
            }
            OrderStatusEnum.DELIVERED -> {
                view.text = parentActivity.getString(R.string.order_Status_delivered)
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