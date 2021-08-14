package cn.ondu.basecommon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import cn.ondu.basecommon.http.ExceptionHandle
import cn.ondu.basecommon.http.HttpStatus
import kotlinx.coroutines.Dispatchers

open class BaseViewModel : ViewModel() {

    protected fun <T> httpToLiveData(
        block: suspend () -> T
    ) = liveData<HttpStatus<T>>(Dispatchers.Main) {
        emit(HttpStatus.LoadingStatus())
        try {
            //repository里已经将异常抛出 这里直接捕获就行
            emit(HttpStatus.SuccessStatus(block()))
        } catch (error: Exception) {
            error.printStackTrace()
            val handleException = ExceptionHandle.handleException(error)
            emit(HttpStatus.ErrorStatus(handleException.errCode,handleException.errorMsg))
        } finally {
            emit(HttpStatus.FinishStatus())
        }
    }

}