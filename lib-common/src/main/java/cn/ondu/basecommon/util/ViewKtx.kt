package cn.ondu.basecommon.util

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import cn.ondu.basecommon.LoadStatus
import cn.ondu.basecommon.base.BaseActivity
import java.util.*

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */

//上一次按下的时间
var lastTapTime = Date().time

//上一次按下的view
var view: View? = null

/**
 * 单击事件
 * @param delay 延迟时间
 */
inline fun View.singTapClick(delay: Long = 800, crossinline singTap: () -> Unit) {
    this.setOnClickListener {
        val currentTime = Date().time
        if (this !== view || currentTime - lastTapTime > delay) {
            singTap()
        }
        lastTapTime = currentTime
        view = this
    }
}


fun ViewGroup.showContentView() {
    LoadStatus.loadingViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.emptyViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.errorViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.contentViewId?.let {
        this.findViewById<View>(it).visibility = View.VISIBLE
    }
}
fun ViewGroup.showLoadingView() {
    LoadStatus.loadingViewId?.let {
        this.findViewById<View>(it).visibility = View.VISIBLE
    }
    LoadStatus.emptyViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.errorViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.contentViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
}

fun ViewGroup.showEmptyView() {
    LoadStatus.loadingViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.emptyViewId?.let {
        this.findViewById<View>(it).visibility = View.VISIBLE
    }
    LoadStatus.errorViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.contentViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
}

fun ViewGroup.showErrorView() {
    LoadStatus.loadingViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.emptyViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.errorViewId?.let {
        this.findViewById<View>(it).visibility = View.VISIBLE
    }
    LoadStatus.contentViewId?.let {
        this.findViewById<View>(it).visibility = View.GONE
    }
}

