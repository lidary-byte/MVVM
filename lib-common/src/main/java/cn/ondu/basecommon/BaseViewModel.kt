package cn.ondu.basecommon

import androidx.lifecycle.*
import cn.ondu.basecommon.http.ExceptionHandle
import cn.ondu.basecommon.http.HttpException
import cn.ondu.basecommon.http.HttpStatus
import cn.ondu.basecommon.http.MutableHttpLiveData
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

open class BaseViewModel : ViewModel() {

    protected fun <T> httpToLiveData(
        httpLiveData: MutableHttpLiveData<T>,
        start: CoroutineDispatcher = Dispatchers.Main,
        block: suspend () -> T
    ) {
        viewModelScope.launch(start) {
            LogUtils.eTag("http", "开始请求")
            httpLiveData.startStatus()
            try {
                //repository里已经将异常抛出 这里直接捕获就行
                val data = block.invoke()
                delay(1000)
                LogUtils.eTag("http", "请求成功")
                httpLiveData.successStatus(data)
            } catch (error: Exception) {
                LogUtils.eTag("http", "请求失败")
                httpLiveData.errorStatus(ExceptionHandle.handleException(error))
            }
        }
    }

    protected fun <T> Flow<HttpStatus<T>>.httpToFlow() = this.onStart {
        emit(HttpStatus.LoadingStatus())
    }.onCompletion {
        emit(HttpStatus.FinishStatus())
    }.flowOn(Dispatchers.Main).catch { throwable ->
        throwable.printStackTrace()
        val handleException = ExceptionHandle.handleException(throwable)
        emit(HttpStatus.ErrorStatus(handleException.errCode, handleException.errorMsg))
    }
}