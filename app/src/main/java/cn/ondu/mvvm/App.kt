package cn.ondu.mvvm

import android.content.Context
import cn.jpush.im.android.api.JMessageClient
import cn.ondu.basecommon.CommApp
import cn.ondu.basecommon.LoadStatus
import cn.ondu.basecommon.http.HttpClient
import cn.ondu.basecommon.http.RetrofitInterface
import cn.ondu.basecommon.util.easyColor
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.tencent.mmkv.MMKV
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


class App : CommApp() {


    override fun onCreate() {
        super.onCreate()
        instance = this
        LoadStatus.apply {
            loadingViewId = R.id.view_loading
            emptyViewId = R.id.view_empty
            errorViewId = R.id.view_error
            errorTextId = R.id.tv_error
        }
        MMKV.initialize(this)
        initJSdk()
        initHttp()
        initSmartRefreshLayout()
    }

    /**
     * 极光sdk初始化
     */
    private fun initJSdk(){
        JMessageClient.setDebugMode(BuildConfig.DEBUG)
        //第二个参数是开启漫游消息
        JMessageClient.init(this,true)
    }

    private fun initSmartRefreshLayout() {
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            layout.setEnableLoadMoreWhenContentNotFull(false) //是否在列表不满一页时候开启上拉加载功能
            layout.setEnableAutoLoadMore(true)
            layout.setEnableLoadMore(true)
            layout.setEnableOverScrollBounce(false)
        }
        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context?, layout: RefreshLayout ->
            //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
            layout.setEnableHeaderTranslationContent(false)
            ClassicsHeader(context).apply {
                setAccentColor(easyColor(R.color.colorPrimary))
            }
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context?, layout: RefreshLayout ->
            layout.setEnableFooterTranslationContent(true)
            ClassicsFooter(context).apply {
                setAccentColor(easyColor(R.color.colorPrimary))
            }
        }
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


