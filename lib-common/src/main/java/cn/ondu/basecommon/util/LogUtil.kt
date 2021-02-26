package cn.ondu.basecommon.util

import android.util.Log
import cn.ondu.basecommon.BaseCommon

/**
 * Log 封装
 * @author ohdu
 */
object LogUtil {

    fun e(msg: String, tag: String = "") {
        if (BaseCommon.debug) {
            Log.e(tag, msg)
        }
    }


    fun i(msg: String, tag: String = "") {
        if (BaseCommon.debug) {
            Log.i(tag, msg)
        }
    }


    fun v(msg: String, tag: String = "") {
        if (BaseCommon.debug) {
            Log.v(tag, msg)
        }
    }


    fun d(msg: String, tag: String = "") {
        if (BaseCommon.debug) {
            Log.d(tag, msg)
        }
    }

    fun w(msg: String, tag: String = "") {
        if (BaseCommon.debug) {
            Log.w(tag, msg)
        }
    }

}