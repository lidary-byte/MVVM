package cn.ondu.basecommon

import androidx.lifecycle.*
import cn.ondu.basecommon.http.ExceptionHandle
import cn.ondu.basecommon.http.HttpStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected fun <T> httpToLiveData(
        block: suspend () -> T,
        result: MutableLiveData<HttpStatus<T>>
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            result.value = HttpStatus.LoadingStatus()
            try {
                result.value = HttpStatus.SuccessStatus(block())
            } catch (error: Exception) {
                error.printStackTrace()
                val handleException = ExceptionHandle.handleException(error)
                result.value =
                    HttpStatus.ErrorStatus(handleException.errCode, handleException.errorMsg)
            } finally {
                result.value = HttpStatus.FinishStatus()
            }
        }

    }


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
            emit(HttpStatus.ErrorStatus(handleException.errCode, handleException.errorMsg))
        } finally {
            emit(HttpStatus.FinishStatus())
        }
    }

}