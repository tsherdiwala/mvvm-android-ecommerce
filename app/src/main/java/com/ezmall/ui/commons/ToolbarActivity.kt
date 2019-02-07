package com.ezmall.ui.commons

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ezmall.R
import com.ezmall.di.DaggerAppcompatActivity
import kotlin.properties.Delegates

abstract class ToolbarActivity<T : ViewDataBinding> : DaggerAppcompatActivity() {

    val toolbar: Toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    protected var hasBack: Boolean by Delegates.observable(false) { _, _, newValue ->
        if (newValue) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    lateinit var dataBinding: T

    abstract val layoutId: Int

    override fun onOptionsItemSelected(item: MenuItem?) =
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
        dataBinding.lifecycleOwner = this
        setSupportActionBar(toolbar)

        if (hasBack) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}