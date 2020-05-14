package com.dengfx.base.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ViewAnimator
import androidx.core.view.children

enum class ViewStatus(val index: Int) {
    LOADING(0),
    FAILED(1),
    EMPTY(2),
    SUCCESS(3)
}


class MultiStateView : ViewAnimator {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val mLayoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    init {

    }
}
