package com.dengfx.base.ui

import androidx.databinding.ViewDataBinding
import com.dengfx.base.R
import com.dengfx.base.databinding.FragmentSmartRefreshLayoutBinding
import com.dengfx.base.vm.BaseViewModel

open class SmartRefreshLayoutFragment<VM : BaseViewModel, VDB : ViewDataBinding>(
    clazz: Class<VM>
) : BaseFragment<VM, FragmentSmartRefreshLayoutBinding>(R.layout.fragment_smart_refresh_layout, clazz) {


}
