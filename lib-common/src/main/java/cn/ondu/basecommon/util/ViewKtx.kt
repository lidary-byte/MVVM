package cn.ondu.basecommon.util

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import cn.ondu.basecommon.R
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

