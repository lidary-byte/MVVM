package cn.ondu.basecommon

import cn.ondu.basecommon.http.HttpException
import cn.ondu.basecommon.http.IBaseBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {
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
}