package com.ezmall.di.modules.uimodules

import com.ezmall.ui.orderdetail.OrderDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OrderDetailModule {

    @ContributesAndroidInjector
    abstract fun providerOrderDetailActivity(): OrderDetailActivity
}