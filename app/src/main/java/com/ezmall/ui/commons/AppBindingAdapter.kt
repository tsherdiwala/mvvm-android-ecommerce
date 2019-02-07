package com.ezmall.ui.commons

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.extension.getParentActivity
import com.ezmall.models.Order
import com.ezmall.ui.orderdetail.OrderDetailAdapter

object AppBindingAdapter {

    @BindingAdapter("order")
    @JvmStatic
    fun setOrderDetail(recyclerView: RecyclerView, item: Order?) {
        with(recyclerView.adapter as? OrderDetailAdapter) {
            this?.order = item
        }
    }

    @BindingAdapter("subOrderNo")
    @JvmStatic
    fun setsubOrderNo(view: TextView, text: String?) {
        val parentActivity = view.getParentActivity()
        view.text = parentActivity?.getString(R.string.sub_order_no, text)
    }

    @BindingAdapter("orderNo")
    @JvmStatic
    fun setorderNo(view: TextView, text: String?) {
        val parentActivity = view.getParentActivity()
        view.text = parentActivity?.getString(R.string.order_no, text)
    }
}



