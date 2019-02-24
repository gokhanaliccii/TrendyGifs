package com.gokhanaliccii.trendygifs.core.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by gokhan.alici on 25.02.2019
 */
abstract class BindableFragment<VB : ViewDataBinding> : Fragment() {

    lateinit var viewBinding: VB

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        onViewBindingReady(savedInstanceState)

        return viewBinding.root
    }

    protected abstract fun onViewBindingReady(savedInstanceState: Bundle?)
}