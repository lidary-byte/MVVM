package cn.ondu.basecommon.util

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
fun Context.easyDrawable(@ColorRes id: Int) = ContextCompat.getDrawable(this, id)
fun Context.easyColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)
fun Context.easyString(@StringRes id: Int) = this.getString(id)
