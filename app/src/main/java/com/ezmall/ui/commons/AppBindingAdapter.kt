package com.ezmall.ui.commons

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.extension.getParentActivity
import com.ezmall.models.Order
import com.ezmall.models.OrderStatus
import com.ezmall.models.OrderStatusEnum
import com.ezmall.ui.orderdetail.OrderDetailAdapter

object AppBindingAdapter {


    @BindingAdapter("order")
    @JvmStatic
    fun setOrderDetail(recyclerView: RecyclerView, item: Order?) {
        with(recyclerView.adapter as? OrderDetailAdapter) {
            this?.order = item
        }
    }

    @BindingAdapter("productPrice")
    @JvmStatic
    fun setProductPrice(view: TextView, text: Float?) {
        val parentActivity = view.getParentActivity()
        view.text = parentActivity?.getString(R.string.rs, text)
    }

    @BindingAdapter("productCouponDiscount")
    @JvmStatic
    fun setProductCouponDiscount(view: TextView, text: Float?) {
        val parentActivity = view.getParentActivity()
        val roundValue = Math.round(text!!)
        view.text = parentActivity?.getString(R.string.rs_minus, roundValue.toString())
    }

    /*@BindingAdapter("mutableTextStatus")
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
    }*/


}



