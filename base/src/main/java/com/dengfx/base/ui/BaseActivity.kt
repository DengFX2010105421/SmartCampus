package com.dengfx.base.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.dengfx.base.OnFragmentInteractionListener


abstract class BaseActivity(@LayoutRes contentLayoutId: Int = 0) :
    AppCompatActivity(contentLayoutId), OnFragmentInteractionListener {
}