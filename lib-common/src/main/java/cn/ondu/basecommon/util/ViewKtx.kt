package cn.ondu.basecommon.util

import android.view.View
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