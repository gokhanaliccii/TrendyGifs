package com.gokhanaliccii.trendygifs.core.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ViewDataBinding
import android.os.Bundle

/**
 * Created by gokhan.alici on 24.02.2019
 */
abstract class BindableViewModelFragment<VB : ViewDataBinding, VM : ViewModel> :
    BindableFragment<VB>() {

    lateinit var viewModel: VM

    protected abstract val viewModelFactory: ViewModelProvider.Factory?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(getViewModel())
    }

    protected abstract fun getViewModel(): Class<VM>
}