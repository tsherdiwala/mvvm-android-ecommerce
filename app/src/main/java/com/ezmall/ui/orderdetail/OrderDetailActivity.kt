package com.ezmall.ui.orderdetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import com.ezmall.databinding.ActivityOrderDetailBinding
import com.ezmall.ui.commons.AppViewModelFactory
import com.ezmall.ui.commons.ToolbarActivity
import javax.inject.Inject

class OrderDetailActivity : ToolbarActivity<ActivityOrderDetailBinding>() {

    override val layoutId = R.layout.activity_order_detail

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(OrderDetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity = this@OrderDetailActivity

        hasBack = true

        setTitle(R.string.order_Detail)


        dataBinding.run {
            orderRV.run {
                adapter = OrderDetailAdapter()
                layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            }
            this.viewModel = activity.viewModel
        }

        viewModel.fetchOrders()

    }

}