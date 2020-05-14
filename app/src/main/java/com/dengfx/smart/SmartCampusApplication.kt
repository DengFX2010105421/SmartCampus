package com.dengfx.smart

import com.dengfx.base.ui.BaseApplication
import com.dengfx.smart.db.AppDataBase

class SmartCampusApplication() : BaseApplication() {

    companion object {
        lateinit var INSTANCE: SmartCampusApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        AppDataBase.getInstance(this)
    }


}