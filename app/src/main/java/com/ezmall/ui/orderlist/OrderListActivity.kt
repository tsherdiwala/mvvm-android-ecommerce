package com.ezmall.ui.orderlist

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.databinding.ActivityOrderListBinding
import com.ezmall.ui.commons.AppViewModelFactory
import com.ezmall.ui.commons.ToolbarActivity
import javax.inject.Inject


private val TAG = OrderListActivity::class.java.simpleName

class OrderListActivity : ToolbarActivity<ActivityOrderListBinding>() {

    override val layoutId = R.layout.activity_order_list

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory


    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(OrderListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTitle(R.string.title_orders)
        val activity = this@OrderListActivity


        dataBinding.run {
            orderListRV.run {
                adapter = OrderListAdapter(mutableListOf(), viewModel)
                layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
            }
            viewModel = activity.viewModel
        }

        viewModel.fetchOrders()
    }
}



