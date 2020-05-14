package com.dengfx.base.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.dengfx.base.R
import com.dengfx.base.databinding.FragmentMultiStateViewBinding
import com.dengfx.base.vm.BaseViewModel
import com.dengfx.base.widget.MultiStateView

open class MultiStateViewFragment<VM : BaseViewModel, VDB : ViewDataBinding>(
    @LayoutRes private val contentLayoutId2: Int = 0,
    clazz: Class<VM>
) : BaseFragment<VM, FragmentMultiStateViewBinding>(R.layout.fragment_multi_state_view, clazz) {

    protected lateinit var multiStateView: MultiStateView

    protected lateinit var viewDataBinding2: VDB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        multiStateView = view as MultiStateView
        if (contentLayoutId2 != 0) {
            viewDataBinding2 = DataBindingUtil.inflate<VDB>(layoutInflater, contentLayoutId2, multiStateView, true)
        }

        multiStateView.children.forEach {
            it.setOnClickListener {
                refresh()
            }
        }

        multiStateView.setOnClickListener {
            refresh()
        }

        multiStateView.performClick()
    }

    protected fun refresh() {
        viewModel.randomNum.observe(viewLifecycleOwner, Observer {
            Thread(Runnable {
                Thread.sleep(1000)
                Handler(Looper.getMainLooper()).post {
                    multiStateView.displayedChild = viewModel.refresh().apply {
                        Toast.makeText(attachedContext, "$it", Toast.LENGTH_SHORT).show()
                    }
                }
            }).start()
        })
    }
}
