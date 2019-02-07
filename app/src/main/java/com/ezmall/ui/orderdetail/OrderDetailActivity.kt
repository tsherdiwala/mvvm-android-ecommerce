package com.ezmall.ui.orderdetail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ezmall.R
import com.ezmall.ui.commons.ToolbarActivity

class OrderDetailActivity : ToolbarActivity<com.ezmall.databinding.ActivityOrderDetailBinding>(){
    override val layoutId = R.layout.activity_order_detail


    private val viewModel by lazy{
        ViewModelProviders.of(this).get(OrderDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //dataBinding.order = viewModel.or

    }

}