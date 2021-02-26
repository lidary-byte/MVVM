package cn.ondu.basecommon.util

import android.content.res.Resources

/**
 * @author: lcc
 * @date: 2021/2/26
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: 屏幕相关
 */

fun Float.px(): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

fun Float.dp():Int{
    val scale = Resources.getSystem().displayMetrics.density
    return (this / scale + 0.5f).toInt()
}
