package cn.ondu.basecommon

import android.app.Application


object BaseCommon {
    internal var debug: Boolean = false
    internal lateinit var application: Application

    fun init(debug: Boolean, application: Application) {
        this.debug = debug
        this.application = application
    }


}