package cn.ondu.basecommon

import android.util.Log

/**
 * Log 封装
 * @author ohdu
 */
object LogUtil {

    fun e(tag: String, msg: String) {
        if (!BaseCommon.debug) {
            Log.e(tag, msg)
        }
    }

    fun e(msg: String) {
        if (!BaseCommon.debug) {
            Log.e(BaseCommon.tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (!BaseCommon.debug) {
            Log.i(tag, msg)
        }
    }

    fun i(msg: String) {
        if (!BaseCommon.debug) {
            Log.i(BaseCommon.tag, msg)
        }
    }

    fun v(tag: String, msg: String) {
        if (!BaseCommon.debug) {
            Log.v(tag, msg)
        }
    }

    fun v(msg: String) {
        if (!BaseCommon.debug) {
            Log.v(BaseCommon.tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (!BaseCommon.debug) {
            Log.d(tag, msg)
        }
    }

    fun d(msg: String) {
        if (!BaseCommon.debug) {
            Log.d(BaseCommon.tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (!BaseCommon.debug) {
            Log.w(tag, msg)
        }
    }

    fun w(msg: String) {
        if (!BaseCommon.debug) {
            Log.w(BaseCommon.tag, msg)
        }
    }

}