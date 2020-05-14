package com.dengfx.base

import android.net.Uri


/**
 * Fragment与Activity交互的接口
 */
interface OnActivityInteractionListener {
    fun onActivityInteraction(uri: Uri) {}
}

/**
 * Fragment与Fragment交互的接口
 */
interface OnFragmentInteractionListener {
    fun onFragmentInteraction(uri: Uri) {}
}



