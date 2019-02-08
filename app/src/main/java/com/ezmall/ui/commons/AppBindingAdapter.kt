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
        view.text = parentActivity?.getString(R.string.rs_minus, text)
    }


}



