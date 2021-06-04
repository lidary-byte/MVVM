package cn.ondu.basecommon.util

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import cn.ondu.basecommon.LoadStatus
import cn.ondu.basecommon.R
import com.blankj.utilcode.util.LogUtils
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

/**
 * 自定义Toast显示
 */
fun Context?.showToast(toastMessage: String?, showDuration: Int = Toast.LENGTH_SHORT) {
    if (this==null){
        return
    }
    toastMessage?.let {
        val toastView = LayoutInflater.from(this).inflate(R.layout.view_toast, null)
        val text = toastView.findViewById(R.id.toast_text) as TextView
        text.text = it
        val toast = Toast(this)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = showDuration
        toast.view = toastView
        toast.show()
    }
}

fun Context?.showDefaultToast(toastMessage: String?, showDuration: Int = Toast.LENGTH_SHORT) {
    if (this==null){
        return
    }
    toastMessage?.let {
        Toast.makeText(this,it,showDuration).show()
    }
}
/**
 * 这几个方法是ContentView 的扩展方法
 * 因为加载失败,加载数据为空的viewId基本可以写死一个
 * 但是ContentView不一定(其实也可以写死 但是不太好)
 */
fun ViewGroup.showContentView() {
    LoadStatus.loadingViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.emptyViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.errorViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.GONE
    }
    this.visibility = View.VISIBLE
}

fun ViewGroup.showLoadingView() {
    LoadStatus.loadingViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.VISIBLE
    }
    LoadStatus.emptyViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.errorViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.GONE
    }
    this.visibility = View.GONE
}

fun ViewGroup.showEmptyView() {
    LoadStatus.loadingViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.emptyViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.VISIBLE
    }
    LoadStatus.errorViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.GONE
    }
    this.visibility = View.GONE

}

fun ViewGroup.showErrorView(errorText: String? = this.context.easyString(R.string.load_error)) {
    LoadStatus.loadingViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.emptyViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.errorViewId?.let {
        this.rootView.findViewById<View>(it).visibility = View.VISIBLE
    }
    LoadStatus.errorTextId?.let { this.rootView.findViewById<TextView>(it).text = errorText }

    this.visibility = View.GONE

}

