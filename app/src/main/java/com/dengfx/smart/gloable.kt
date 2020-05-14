package com.dengfx.smart

import com.google.gson.Gson
import com.google.gson.GsonBuilder


val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
val ARG_PARAM = "ARG_PARAM"