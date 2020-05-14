package com.dengfx.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dengfx.base.OnActivityInteractionListener
import com.dengfx.base.OnFragmentInteractionListener
import com.dengfx.base.vm.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding>(
    @LayoutRes private val contentLayoutId: Int = 0,
    private val clazz: Class<VM>
) : Fragment(0), OnFragmentInteractionListener, OnActivityInteractionListener {

    protected lateinit var viewModel: VM

    protected lateinit var viewDataBinding: VDB

    protected lateinit var attachedContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        attachedContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = generateViewModel()
    }

    protected open fun generateViewModel(): VM = ViewModelProvider(this).get(clazz)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate<VDB>(inflater, contentLayoutId, container, false)
        return viewDataBinding.root
    }
}