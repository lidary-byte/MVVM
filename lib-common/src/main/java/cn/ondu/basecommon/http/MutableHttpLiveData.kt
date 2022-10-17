package cn.ondu.basecommon.http

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


/**
 * 禁止在LiveData里发送finish状态
 * 保证最后一个状态要么是error要么是success
 * 防止当前activity从onPause->onStart状态时导致的success或error状态丢失
 * 因此finish状态在监听时分别在success和error状态后单独调一下
 *
 */
class MutableHttpLiveData<T> : MutableLiveData<HttpStatus<T>>() {

    fun startStatus(mainThread: Boolean = false) {
        if (mainThread) {
            postValue(HttpStatus.LoadingStatus())
        } else {
            value = HttpStatus.LoadingStatus()
        }
    }

    fun successStatus(data: T, mainThread: Boolean = false) {
        if (mainThread) {
            postValue(HttpStatus.SuccessStatus(data))
        } else {
            value = HttpStatus.SuccessStatus(data)
        }
    }

    fun errorStatus(exception: Exception, mainThread: Boolean = false) {
        val handleException = ExceptionHandle.handleException(exception)
        if (mainThread) {
            postValue(HttpStatus.ErrorStatus(handleException.errCode, handleException.errorMsg))
        } else {
            value = HttpStatus.ErrorStatus(handleException.errCode, handleException.errorMsg)
        }
    }

    @Deprecated("禁止使用", level = DeprecationLevel.ERROR)
    fun finishStatus(mainThread: Boolean = false) {
        if (mainThread) {
            postValue(HttpStatus.FinishStatus())
        } else {
            value = HttpStatus.FinishStatus()
        }
    }


    inline fun observeStatus(
        owner: LifecycleOwner, crossinline callBack: HttpLiveDataCallBack<T>.() -> Unit
    ) {
        val requestCallback = HttpLiveDataCallBack<T>().apply(callBack)
        this.observe(owner, Observer<HttpStatus<T>> {
            when (it) {
                is HttpStatus.LoadingStatus -> requestCallback.startCallBack?.invoke()
                is HttpStatus.SuccessStatus -> {
                    requestCallback.successCallBack?.invoke(it.data!!)
                    requestCallback.finishCallBack?.invoke()
                }
                is HttpStatus.ErrorStatus -> {
                    requestCallback.errorCallBack?.invoke(it.code, it.message)
                    requestCallback.finishCallBack?.invoke()
                }
                else -> {}
            }
        })
    }
}

class HttpLiveDataCallBack<T> {
    var startCallBack: (() -> Unit)? = null
    var successCallBack: ((T) -> Unit)? = null
    var errorCallBack: ((code: Int, message: String) -> Unit)? = null
    var finishCallBack: (() -> Unit)? = null


    fun onStart(startCallBack: (() -> Unit)? = null) {
        this.startCallBack = startCallBack
    }

    fun onSuccess(successCallBack: ((T) -> Unit)? = null) {
        this.successCallBack = successCallBack
    }

    fun onError(errorCallBack: ((code: Int, message: String) -> Unit)? = null) {
        this.errorCallBack = errorCallBack
    }

    fun onFinish(finishCallBack: (() -> Unit)? = null) {
        this.finishCallBack = finishCallBack
    }
}
