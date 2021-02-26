package cn.ondu.basecommon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import cn.ondu.basecommon.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class BaseViewModel : ViewModel(){
    /**
     * 一些比较复杂处理,比如多个接口的并发请求 则通过外部实现block()方法来完成
     */
    protected fun <T> httpComplex(
        resultLiveData: MutableLiveData<HttpStatus>,
        block: suspend FlowCollector<T>.() -> Unit
    ) = liveData(Dispatchers.Main) {
        flow<T> {
            block()
        }
            .flowOn(Dispatchers.IO)
            .onStart {
                resultLiveData.value = LoadStatus()
            }
            .catch { error ->
                resultLiveData.value = ErrorStatus(error.message ?: "")
            }
            .onCompletion {
                resultLiveData.value = FinishStatus()
            }
            .collect {
                emit(it)
            }

    }


    protected fun <T> http(
        resultLiveData: MutableLiveData<HttpStatus>,
        block: suspend () -> BaseBean<T>
    ) = liveData(Dispatchers.Main) {
        flow {
            val httpResult = block()
            if (httpResult.isSuccess()) {
                emit(httpResult.data)
            } else {
                throw Exception(httpResult.errorMsg)
            }
        }
            .flowOn(Dispatchers.IO)
            .onStart {
                resultLiveData.value = LoadStatus()
            }
            .catch { error ->
                resultLiveData.value = ErrorStatus(error.message ?: "")
            }
            .onCompletion {
                resultLiveData.value = FinishStatus()
            }
            .collect {
                emit(it)
            }

    }
}