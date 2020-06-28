package cn.ondu.basecommon

import android.content.Context

object BaseCommon {
    private var mContext: Context? = null
    internal var debug: Boolean = false
    internal var tag = BaseCommon::class.java.simpleName


    fun init(mContext: Context) {
        this.mContext = mContext
    }

    fun init(mContext: Context, debug: Boolean) {
        this.mContext = mContext
        this.debug = debug
    }


}