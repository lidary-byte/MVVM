package cn.ondu.basecommon.http

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


/**
 * @author: lcc
 * @date: 2021/2/26
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: http加载状态
 */
sealed class HttpStatus<T>(
    val data: T? = null,
    val code: Int = -1,
    val message: String = ""
) {
    class SuccessStatus<T>(data: T) : HttpStatus<T>(data)
    class LoadingStatus<T> : HttpStatus<T>()
    class FinishStatus<T> : HttpStatus<T>()
    class ErrorStatus<T>(code: Int, message: String) : HttpStatus<T>(code = code, message = message)
}

/**
 * http状态解析
 * onHttpFinish() 请在最后一个调用
 */
inline fun <T> HttpStatus<T>.httpStatusParsing(
    onLoading: () -> Unit = {},
    onError: (code: Int, message: String) -> Unit = { code, message -> },
    onFinish: () -> Unit = {},
    onSuccess: (t: T?) -> Unit
) {
    when (this) {
        is HttpStatus.LoadingStatus -> onLoading()
        is HttpStatus.SuccessStatus -> onSuccess(this.data)
        is HttpStatus.ErrorStatus -> onError(this.code, this.message)
        is HttpStatus.FinishStatus -> onFinish()
    }
}

inline fun <T> HttpStatus<T>.onHttpStart(onLoading: () -> Unit): HttpStatus<T> {
    if (this is HttpStatus.LoadingStatus) {
        onLoading()
    }
    return this

}

inline fun <T> HttpStatus<T>.onHttpSuccess(onSuccess: (data: T?) -> Unit): HttpStatus<T> {
    if (this is HttpStatus.SuccessStatus) {
        onSuccess(this.data)
    }
    return this

}

inline fun <T> HttpStatus<T>.onHttpError(onError: (code: Int, message: String) -> Unit): HttpStatus<T> {
    if (this is HttpStatus.ErrorStatus) {
        onError(this.code, this.message)
    }
    return this

}

inline fun <T> HttpStatus<T>.onHttpFinish(onFinish: () -> Unit) {
    if (this is HttpStatus.FinishStatus) {
        onFinish()
    }
}


/**
 * 针对flow请求
 * onHttpFinish() 请在最后一个调用
 */

suspend inline fun <T> Flow<HttpStatus<T>>.onHttpStart(crossinline onLoading: () -> Unit): Flow<HttpStatus<T>> {
    this.collect {
        if (it is HttpStatus.LoadingStatus) {
            onLoading()
        }
    }
    return this
}

suspend inline fun <T> Flow<HttpStatus<T>>.onHttpSuccess(crossinline onSuccess: (data: T?) -> Unit): Flow<HttpStatus<T>> {
    this.collect {
        if (it is HttpStatus.SuccessStatus) {
            onSuccess(it.data)
        }
    }
    return this
}

suspend inline fun <T> Flow<HttpStatus<T>>.onHttpError(crossinline onError: (code: Int, message: String) -> Unit): Flow<HttpStatus<T>>  {
    this.collect {
        if (it is HttpStatus.ErrorStatus) {
            onError(it.code, it.message)
        }
    }
    return this
}

suspend inline fun <T> Flow<HttpStatus<T>>.onHttpFinish(crossinline onFinish: () -> Unit) {
    this.collect {
        if (it is HttpStatus.FinishStatus) {
            onFinish()
        }
    }
}


