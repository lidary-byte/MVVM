package cn.ondu.basecommontest

import cn.ondu.basecommon.BaseCommon
import cn.ondu.basecommon.CommApp
import cn.ondu.basecommon.http.HttpClient
import cn.ondu.basecommon.http.RetrofitInterface
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class App : CommApp() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        BaseCommon.init(true, this)
        initHttp()
    }

    private fun initHttp() {
        HttpClient.retrofitInterface = object : RetrofitInterface {
            override fun releaseHost(): String = Config.HOST_URL
            override fun debugHost(): String = Config.HOST_URL
            override fun isPrintLog(): Boolean = Config.APP_DEBUG
            override fun isDebug(): Boolean = Config.APP_DEBUG
        }
    }

    companion object {
        private var instance: App by NotNullAppInstance()
        fun getAppInstance() = instance
    }

    private class NotNullAppInstance<App> : ReadWriteProperty<Companion, App> {
        private var mApp: App? = null
        override fun setValue(thisRef: Companion, property: KProperty<*>, value: App) {
            if (mApp == null) {
                mApp = value
                return
            }
            throw IllegalStateException("App不能被多次赋值")
        }

        override fun getValue(thisRef: Companion, property: KProperty<*>): App {
            return mApp ?: throw NullPointerException("App不能为null")
        }

    }
}


