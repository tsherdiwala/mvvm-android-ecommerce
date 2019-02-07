package com.ezmall.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ezmall.R
import com.ezmall.databinding.ActivityMainBinding
import com.ezmall.ui.commons.ToolbarActivity
import com.ezmall.ui.orderdetail.OrderDetailActivity
import com.ezmall.ui.orderlist.OrderListActivity

class MainActivity : ToolbarActivity<ActivityMainBinding>(), Navigator {


    override val layoutId = R.layout.activity_main

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding.viewModel = viewModel

        val activity = this

        with(viewModel){
            navigateOrderDetail.observe(activity, Observer {
                goToOrderDetail()
            })

            navigateOrderList.observe(activity, Observer {
                goToOrderList()
            })
        }
    }

    override fun goToOrderList() {
        startActivity(
            Intent(this, OrderListActivity::class.java)
        )
    }

    override fun goToOrderDetail() {
        startActivity(
            Intent(this,OrderDetailActivity::class.java)
        )
    }
}
