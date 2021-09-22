package cn.ondu.basecommon.http

import android.util.MalformedJsonException
import com.squareup.moshi.JsonDataException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import java.net.ConnectException
import java.text.ParseException

class HttpException : Exception {

    var errorMsg: String //错误消息
    var errCode: Int = 0 //错误码

    constructor(errCode: Int, error: String?) : super(error) {
        this.errorMsg = error ?: "请求失败，请稍后再试"
        this.errCode = errCode
    }

    constructor(error: Error, e: Throwable?) {
        errCode = error.getKey()
        errorMsg = error.getValue()
    }
}

object ExceptionHandle {

    fun handleException(e: Throwable): HttpException = when (e) {
        is retrofit2.HttpException -> HttpException(Error.NETWORK_ERROR, e)
        is JSONException, is JsonDataException, is ParseException, is MalformedJsonException -> HttpException(Error.PARSE_ERROR, e)
        is ConnectException -> HttpException(Error.NETWORK_ERROR, e)
        is javax.net.ssl.SSLException -> HttpException(Error.SSL_ERROR, e)
        is ConnectTimeoutException -> HttpException(Error.TIMEOUT_ERROR, e)
        is java.net.SocketTimeoutException -> HttpException(Error.TIMEOUT_ERROR, e)
        is java.net.UnknownHostException -> HttpException(Error.TIMEOUT_ERROR, e)
        is HttpException -> e
        else -> HttpException(Error.UNKNOWN, e)
    }
}

/**
 * 错误枚举类
 */
enum class Error(private val code: Int, private val err: String) {

    /**
     * 未知错误
     */
    UNKNOWN(1000, "请求失败，请稍后再试"),

    /**
     * 解析错误
     */
    PARSE_ERROR(1001, "Json解析错误，请稍后再试"),

    /**
     * 网络错误
     */
    NETWORK_ERROR(1002, "网络连接错误，请稍后重试"),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, "证书出错，请稍后再试"),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, "网络连接超时，请稍后重试");

    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}