package com.ezmall.ui.commons

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ezmall.extension.getParentActivity
import com.ezmall.models.OrderStatusEnum
import com.ezmall.models.Product

@BindingAdapter("mutableProductText")
fun setMutableProductText(view: TextView, text: MutableLiveData<Map<Product, Int>>?) {

    val parentActivity = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(
            parentActivity,
            Observer {
                it.forEach {
                    view.text = it.key.name ?: ""
                }
            }
        )
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {

    val parentActivity = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(
            parentActivity,
            Observer {
                view.text = it ?: ""
            }
        )
    }
}

@BindingAdapter("mutableTextStatus")
fun setMutableTextStatus(view: TextView, text: MutableLiveData<String>?) {

    val parentActivity = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(
            parentActivity,
            Observer {
                    view.text = it ?: ""
                if (it.equals(OrderStatusEnum.DELIVERED))
                    view.setTextColor(ContextCompat.getColor(parentActivity,android.R.color.holo_blue_bright))
                if (it.equals(OrderStatusEnum.SHIPPED))
                    view.setTextColor(ContextCompat.getColor(parentActivity,android.R.color.holo_green_dark))
                if (it.equals(OrderStatusEnum.ORDERED))
                    view.setTextColor(ContextCompat.getColor(parentActivity,android.R.color.holo_orange_light))
                if (it.equals(OrderStatusEnum.READY_TO_SHIP))
                    view.setTextColor(ContextCompat.getColor(parentActivity,android.R.color.holo_purple))
                if (it.equals(OrderStatusEnum.CANCELLED))
                    view.setTextColor(ContextCompat.getColor(parentActivity,android.R.color.holo_red_dark))

            }
        )
    }
}

@BindingAdapter("mutableTextFloat")
fun setMutableTextFloat(view: TextView, text: MutableLiveData<Float>?) {

    val parentActivity = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(
            parentActivity,
            Observer {
                view.text = it.toString() ?: ""
            }
        )
    }
}