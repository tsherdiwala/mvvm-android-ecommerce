package com.ezmall.ui.orderlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.databinding.ActivityOrderListBinding
import com.ezmall.databinding.ItemOrderListBinding
import com.ezmall.models.Order
import com.ezmall.models.OrderStatusEnum
import com.ezmall.ui.commons.AppViewModelFactory
import com.ezmall.ui.commons.ToolbarActivity
import javax.inject.Inject


private val TAG = OrderListActivity::class.java.simpleName

class OrderListActivity : ToolbarActivity<ActivityOrderListBinding>(), Navigator {

    override val layoutId = R.layout.activity_order_list

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory


    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(OrderListViewModel::class.java)
    }

    lateinit var orderListAdapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.viewModel = viewModel
        orderListAdapter = OrderListAdapter()

        with(viewModel) {
            val activity = this@OrderListActivity
            navigateFetchOrderListSuccess.observe(activity, Observer {
                onSuccess(it)
            })
            navigateFetchOrderListError.observe(activity, Observer {
                onError(it)
            })
        }
        dataBinding.orderListRV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        dataBinding.orderListRV.adapter = orderListAdapter
        dataBinding.orderListRV.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        viewModel.fetchOrders()


    }

    override fun onSuccess(list: List<Order>) {
        orderListAdapter.update(list)
    }

    override fun onError(it: Throwable) {
        Log.e(TAG, it.message)
    }
}

class OrderListAdapter : RecyclerView.Adapter<OrderListAdapter.OrderListVH>() {

    private lateinit var orderList: List<Order>

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

    fun update(it: List<Order>?) {
        orderList = it!!
        notifyDataSetChanged()
    }


    inner class OrderListVH(private val view: ItemOrderListBinding) : RecyclerView.ViewHolder(view.root) {

        private val orderViewModel = OrderViewModel()

        fun bind(order: Order) {
            if (order.currentOrderStatus.orderStatus == OrderStatusEnum.DELIVERED) {
                view.orderListOpenIssueTV.visibility = View.VISIBLE
            }
            orderViewModel.bind(order)
            view.orderViewModel = orderViewModel
        }

    }
}

