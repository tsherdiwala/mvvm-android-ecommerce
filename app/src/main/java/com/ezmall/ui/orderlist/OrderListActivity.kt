package com.ezmall.ui.orderlist

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.ezmall.R
import com.ezmall.databinding.ActivityOrderListBinding
import com.ezmall.ui.commons.AppViewModelFactory
import com.ezmall.ui.commons.ToolbarActivity
import javax.inject.Inject

class OrderListActivity : ToolbarActivity<ActivityOrderListBinding>() {

    override val layoutId = R.layout.activity_order_list

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory


    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(OrderListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.viewModel = viewModel

        with(viewModel){
            fetchOrders()
        }

    }

}