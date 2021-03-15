package cn.ondu.basecommon

import androidx.lifecycle.*
import cn.ondu.basecommon.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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
                    resultLiveData.value = HttpStatus.ErrorStatus(error.message ?: "")
                }
                .onCompletion {
                    resultLiveData.value = HttpStatus.FinishStatus()
                }
                .collect {
                    resultLiveData.value = HttpStatus.SuccessStatus(it)
                }
        }

    }


    protected fun <T> http(
        resultLiveData: MutableLiveData<HttpStatus<T>>,
        block: suspend () -> BaseBean<T>
    ) {
        viewModelScope.launch(Dispatchers.Main) {
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
                    resultLiveData.value = HttpStatus.LoadingStatus()
                }
                .catch { error ->
                    resultLiveData.value = HttpStatus.ErrorStatus(error.message ?: "")
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