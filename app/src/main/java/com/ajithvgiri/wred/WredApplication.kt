package com.ajithvgiri.wred

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class WredApplication: Application(){
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}