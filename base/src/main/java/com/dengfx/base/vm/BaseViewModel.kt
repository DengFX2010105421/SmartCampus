package com.dengfx.base.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

abstract class BaseViewModel : ViewModel() {

    private val _randomNum: LiveData<Int> = MutableLiveData<Int>().apply {
        value = Random.nextInt(3) + 1
    }

    var randomNum: LiveData<Int> = _randomNum

    fun refresh(): Int = 3

}