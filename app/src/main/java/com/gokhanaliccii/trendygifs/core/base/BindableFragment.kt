package com.gokhanaliccii.trendygifs.core.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by gokhan.alici on 24.02.2019
 */
abstract class BindableFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {

    lateinit var viewBinding: VB
    lateinit var viewModel: VM

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract val viewModelFactory: ViewModelProvider.Factory?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(getViewModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        onViewBindingReady(savedInstanceState)

        return viewBinding.root
    }

    protected abstract fun onViewBindingReady(savedInstanceState: Bundle?)

    protected abstract fun getViewModel(): Class<VM>
}