package cn.ondu.basecommon.util

import android.content.res.Resources
import android.util.TypedValue
import com.blankj.utilcode.util.ConvertUtils

/**
 * @author: lcc
 * @date: 2021/2/26
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: 屏幕相关
 */

/**
 * dp转px
 */
val Number.px
        get() =  ConvertUtils.dp2px(this.toFloat())

/**
 * px转dp
 */
val Number.dp
    get() =  ConvertUtils.px2dp(this.toFloat())

val Number.sp
    get() = ConvertUtils.px2sp(this.toFloat())


