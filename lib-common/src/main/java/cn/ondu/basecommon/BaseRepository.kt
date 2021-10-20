package cn.ondu.basecommon

import cn.ondu.basecommon.http.ExceptionHandle
import cn.ondu.basecommon.http.HttpException
import cn.ondu.basecommon.http.HttpStatus
import cn.ondu.basecommon.http.IBaseBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

open class BaseRepository {

    /**
     * 数据脱壳
     */
    suspend fun <T> parsData(
        block: suspend () -> IBaseBean<T>
    ): T {
        val httpResult = withContext(Dispatchers.IO) {
            block()
        }
        if (httpResult.isSuccess()) {
            return httpResult.data()
        } else {
            throw HttpException(httpResult.errorCode(), httpResult.errorMsg())
        }
    }

    fun <T> parsDataToFlow(block: suspend () -> IBaseBean<T>) = flow<HttpStatus<T>> {
        val httpResult = withContext(Dispatchers.IO) {
            block()
        }
        if (httpResult.isSuccess()) {
            emit(HttpStatus.SuccessStatus(httpResult.data()))
        } else {
            throw HttpException(httpResult.errorCode(), httpResult.errorMsg())
        }
    }
}