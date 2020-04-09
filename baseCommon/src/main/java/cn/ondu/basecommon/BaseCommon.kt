package cn.ondu.basecommon

import android.content.Context

object BaseCommon {
    var mContext: Context? = null
    var debug: Boolean = false
    var tag = BaseCommon::class.java.simpleName


    fun init(mContext: Context) {
        this.mContext = mContext
    }

    fun init(mContext: Context, debug: Boolean) {
        this.mContext = mContext
        this.debug = debug
    }


}