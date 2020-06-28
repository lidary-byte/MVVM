package cn.ondu.basecommontest

import android.app.Application
import cn.ondu.basecommon.BaseCommon

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        BaseCommon.init(this, true)
    }
}