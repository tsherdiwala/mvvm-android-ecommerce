package com.ezmall.ui.commons

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.extension.getParentActivity
import com.ezmall.models.IssueStatus
import com.ezmall.models.Order
import com.ezmall.models.OrderStatus
import com.ezmall.models.OrderStatusEnum
import com.ezmall.ui.orderdetail.OrderDetailAdapter
import java.text.SimpleDateFormat
import java.util.*

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

    @BindingAdapter("currentStatus")
    @JvmStatic
    fun setCurrentStatus(view: ImageView, status: OrderStatus) {

        val parentActivity = view.getParentActivity()
        if (status.isCompleted)
            view.setImageDrawable(ContextCompat.getDrawable(parentActivity!!, R.drawable.ic_check_circle))
        else
            view.setImageDrawable(ContextCompat.getDrawable(parentActivity!!, R.drawable.ic_circle))
    }

    @BindingAdapter("productImageUrl")
    @JvmStatic
    fun setMutableImageUrl(view: ImageView, url: String?) {
        val parentActivity = view.getParentActivity()
        if (parentActivity != null && url != null) {
            GlideApp.with(view)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view)
        }
    }

    @BindingAdapter(value = ["date", "dateFormat", "prefix", "currentOrderStatus"], requireAll = false)
    @JvmStatic
    fun setDate(view: TextView, date: Date?, format: String?, prefix: Int?, status: OrderStatusEnum?) {

        val parentActivity = view.getParentActivity()
        if (parentActivity != null) {

            (format ?: "dd/MM/yy").run {
                SimpleDateFormat(this, Locale.ENGLISH).run {
                    if (prefix != null && status != OrderStatusEnum.ORDERED)
                        view.text = parentActivity.getString(prefix, format(date))
                    else
                        view.text = format(date)
                }
            }
        }
    }

    @BindingAdapter("orderStatus")
    @JvmStatic
    fun setMutableTextStatus(view: TextView, orderStatus: OrderStatusEnum?) {

        val parentActivity = view.getParentActivity()
        if (parentActivity != null && orderStatus != null) {

            when (orderStatus) {
                OrderStatusEnum.ORDERED -> {
                    view.text = parentActivity.getString(R.string.order_status_ordered)
                    view.setTextColor(ContextCompat.getColor(parentActivity, android.R.color.holo_green_dark))
                }
                OrderStatusEnum.READY_TO_SHIP -> {
                    view.text = parentActivity.getString(R.string.order_status_ready_to_ship)
                }
                OrderStatusEnum.SHIPPED -> {
                    view.text = parentActivity.getString(R.string.order_Status_shipped)
                }
                OrderStatusEnum.DELIVERED -> {
                    view.text = parentActivity.getString(R.string.order_Status_delivered)
                }
            }
        }
    }


    @BindingAdapter("issueStatus")
    @JvmStatic
    fun setBackground(view: LinearLayout, text: IssueStatus) {

        val parentActivity = view.getParentActivity()

        if (parentActivity != null) {
            if (text == IssueStatus.OPEN)
                view.background = ContextCompat.getDrawable(parentActivity, R.drawable.bg_open_list_issue)
            else
                view.background = ContextCompat.getDrawable(parentActivity, R.drawable.bg_close_list_issue)

        }
    }
}





