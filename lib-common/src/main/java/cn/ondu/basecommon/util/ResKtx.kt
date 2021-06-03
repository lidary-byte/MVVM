package cn.ondu.basecommon.util

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.easyColor(@ColorRes id: Int)  =ContextCompat.getColor(this, id)