package cn.ondu.basecommon.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import cn.ondu.basecommon.R
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import java.util.*

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */


/**
 * 防抖点击
 * @param delay 延迟时间
 */
var viewClickEnableTag = false
inline fun View.singTapClick(delay: Long = 800, crossinline singTap: () -> Unit) {
    this.setOnClickListener {
        viewClickEnableTag = false
        singTap()
        postDelayed({ viewClickEnableTag = true }, delay)
    }
}


/**
 * 防抖点击
 * view.clickFlow()
.throttleFirst(300)
.onEach { // 点击事件响应 }
.launchIn(mainScope)
 */
fun View.clickFlow() = callbackFlow<View> {
    setOnClickListener { offer(it) }
    awaitClose { setOnClickListener(null) }
}


/**
 * 构建输入框文字变化流(一般用于实时搜索)
 *  this.textChangeFlow()
 *   .filter { it.isNotEmpty() } //过滤掉空的
 *   .debounce(timeout)
 *   .flatMapLatest {  }
 *   .flowOn(Dispatchers.IO) // 让搜索在异步线程中执行
 *   .onEach { updateUi(it) } // 获取搜索结果并更新界面
 *   .launchIn(mainScope) // 在主线程收集搜索结果
 */
fun EditText.textChangeFlow(): Flow<String> = callbackFlow {
    // 构建输入框监听器
    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        // 在文本变化后向流发射数据
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let { offer(it.toString()) }
        }
    }
    this@textChangeFlow.addTextChangedListener(watcher)
    awaitClose { this@textChangeFlow.removeTextChangedListener(watcher) } // 阻塞以保证流一直运行
}

/**
 * 自定义Toast显示
 */
fun Context?.showToast(toastMessage: String?, showDuration: Int = Toast.LENGTH_SHORT) {
    if (this == null) {
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
    if (this == null) {
        return
    }
    toastMessage?.let {
        Toast.makeText(this, it, showDuration).show()
    }
}

