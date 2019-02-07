package com.ezmall.ui.commons

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.models.Order
import com.ezmall.ui.orderdetail.OrderDetailAdapter

object AppBindingAdapter {

    @BindingAdapter("order")
    @JvmStatic
    fun setOrder(recyclerView: RecyclerView, order: Order?){
        (recyclerView.adapter as? OrderDetailAdapter)?.order = order
    }

}