package cn.ondu.basecommon.http


/**
 * @author: lcc
 * @date: 2021/2/26
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: http加载状态
 */
sealed class HttpStatus<T>(
    val data: T? = null,
    val message: String? = null
) {
    class SuccessStatus<T>(data: T) : HttpStatus<T>(data)
    class LoadingStatus<T> : HttpStatus<T>()
    class FinishStatus<T> : HttpStatus<T>()
    class ErrorStatus<T>(message: String) : HttpStatus<T>(message = message)
}

/**
 * http状态解析
 */
inline fun <T> HttpStatus<T>.httpStatusParsing(
    onLoading: () -> Unit = {},
    onError: (message: String?) -> Unit = {},
    onFinish: () -> Unit = {},
    onSuccess: (t: T?) -> Unit
) {
    when (this) {
        is HttpStatus.LoadingStatus -> onLoading()
        is HttpStatus.SuccessStatus -> onSuccess(this.data)
        is HttpStatus.ErrorStatus -> onError(this.message)
        is HttpStatus.FinishStatus -> onFinish()
    }
}

inline fun <T> HttpStatus<T>.onStart(onLoading: () -> Unit): HttpStatus<T> {
    if (this is HttpStatus.LoadingStatus) {
        onLoading()
    }
    return this

}

inline fun <T> HttpStatus<T>.onSuccess(onSuccess: (data: T?) -> Unit): HttpStatus<T> {
    if (this is HttpStatus.SuccessStatus) {
        onSuccess(this.data)
    }
    return this

}

inline fun <T> HttpStatus<T>.onError(onError: (message: String?) -> Unit): HttpStatus<T> {
    if (this is HttpStatus.ErrorStatus) {
        onError(this.message)
    }
    return this

}

inline fun <T> HttpStatus<T>.onFinish(onFinish: () -> Unit): HttpStatus<T> {
    if (this is HttpStatus.FinishStatus) {
        onFinish()
    }
    return this

}