package com.ezmall.di.modules.uimodules

import com.ezmall.ui.orderlist.OrderListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OrderListModule {
    @ContributesAndroidInjector
    abstract fun provideOrderListActivity(): OrderListActivity
}