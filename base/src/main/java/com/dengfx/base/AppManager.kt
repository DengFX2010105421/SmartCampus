package com.dengfx.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.dengfx.base.ui.BaseActivity
import java.util.*

object AppManager {

    private val mActivityStack = Stack<BaseActivity>()
    private val mFragmentStack = Stack<BaseActivity>()

    fun addActivity(activity: BaseActivity) {
        mActivityStack.add(activity)
    }

    fun removeActivity(activity: BaseActivity) {
        mActivityStack.remove(activity)
    }

    fun addFragment(fragment: BaseActivity) {
        mFragmentStack.add(fragment)
    }

    fun removeFragment(fragment: BaseActivity) {
        mFragmentStack.remove(fragment)
    }

    fun removeAllActivity() {
        for (activity in mActivityStack) {
            activity.finish()
        }
        mActivityStack.clear()
    }

    fun <T : BaseActivity> hasActivity(tClass: Class<T>): Boolean {
        for (activity in mActivityStack) {
            if (tClass.name == activity::class.java.name) {
                return !activity.isDestroyed || !activity.isFinishing
            }
        }
        return false
    }

    private fun hideSoftKeyBoard(activity: BaseActivity) {
        val localView = activity.currentFocus
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        if (localView != null && imm != null) {
            imm.hideSoftInputFromWindow(localView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}
