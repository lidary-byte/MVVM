package cn.ondu.basecommon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import cn.ondu.basecommon.http.ExceptionHandle
import cn.ondu.basecommon.http.HttpException
import cn.ondu.basecommon.http.IBaseBean
import cn.ondu.basecommon.http.HttpStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {
    /**
     * 一些比较复杂处理,比如多个接口的并发请求 则通过外部实现block()方法来完成
     */
    protected fun <T> httpComplex(
        resultLiveData: MutableLiveData<HttpStatus<T>>,
        block: suspend FlowCollector<T>.() -> Unit
    ) {

        viewModelScope.launch(Dispatchers.Main) {
            flow<T> {
                block()
            }
                .flowOn(Dispatchers.IO)
                .onStart {
                    resultLiveData.value = HttpStatus.LoadingStatus()
                }
                .catch { error ->
                    val handleException = ExceptionHandle.handleException(error)
                    resultLiveData.value = HttpStatus.ErrorStatus(handleException.message ?: "")
                }
                .onCompletion {
                    resultLiveData.value = HttpStatus.FinishStatus()
                }
                .collect {
                    resultLiveData.value = HttpStatus.SuccessStatus(it)
                }
        }

    }

    protected fun <T> httpToLiveData(
        block: suspend () -> IBaseBean<T>
    ) = liveData<HttpStatus<T>>(Dispatchers.Main) {
        emit(HttpStatus.LoadingStatus())
        try {
            val httpResult = withContext(Dispatchers.IO) {
                block()
            }
            if (httpResult.isSuccess()) {
                emit(HttpStatus.SuccessStatus(httpResult.data()))
            } else {
                throw HttpException(httpResult.errorCode(), httpResult.errorMsg())
            }
        } catch (error: Exception) {
            error.printStackTrace()
            val handleException = ExceptionHandle.handleException(error)
            emit(HttpStatus.ErrorStatus(handleException.errorMsg))
        } finally {
            emit(HttpStatus.FinishStatus())
        }
    }

    protected fun <T> http(
        resultLiveData: MutableLiveData<HttpStatus<T>>,
        block: suspend () -> IBaseBean<T>
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            flow {
                val httpResult = block()
                if (httpResult.isSuccess()) {
                    emit(httpResult.data())
                } else {
                    throw Exception(httpResult.errorMsg())
                }
            }
                .flowOn(Dispatchers.IO)
                .onStart {
                    resultLiveData.value = HttpStatus.LoadingStatus()
                }
                .catch { error ->
                    val handleException = ExceptionHandle.handleException(error)
                    resultLiveData.value = HttpStatus.ErrorStatus(handleException.message ?: "")
                }
                .onCompletion {
                    resultLiveData.value = HttpStatus.FinishStatus()
                }
                .collect {
                    resultLiveData.value = HttpStatus.SuccessStatus(it)
                }
        }

    }
}