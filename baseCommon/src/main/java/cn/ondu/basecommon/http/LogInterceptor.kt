package cn.ondu.basecommon.http

import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okio.Buffer
import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException
import java.util.concurrent.TimeUnit

class LogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        //log 信息
        val builder = StringBuilder()
        val request = chain.request()
        val requestBody = request.body()
        val hasRequestBody = requestBody != null
        val connection = chain.connection()
        val protocol =
            if (connection != null) connection.protocol() else Protocol.HTTP_1_1
        val requestStartMessage = """
            请求方式 ----> ${request.method()}
            请求地址: ${request.url()}
            Http 版本:$protocol
            """.trimIndent()
        builder.append(requestStartMessage)
        if (hasRequestBody) {
            if (requestBody!!.contentType() != null) {
                builder.append(
                    """

    请求头 ----> Content-Type: ${requestBody.contentType()}
    """.trimIndent()
                )
            }
        }
        builder.append("\n请求参数 ----> ")
        val headers = request.headers()
        var i = 0
        val count = headers.size()
        while (i < count) {
            val name = headers.name(i)
            // Skip headers from the request body as they are explicitly logged above.
            if (!"Content-Type".equals(name, ignoreCase = true) && !"Content-Length".equals(
                    name,
                    ignoreCase = true
                )
            ) {
                builder.append(name + ": " + headers.value(i))
            }
            i++
        }
        if (!hasRequestBody) {
            builder.append(
                """

    请求结束 --> END ${request.method()}
    """.trimIndent()
            )
        } else if (bodyEncoded(request.headers())) {
            builder.append(
                """
请求结束 --> END ${request.method()} (encoded body omitted)"""
            )
        } else {
            val buffer = Buffer()
            requestBody!!.writeTo(buffer)
            var charset = UTF8
            val contentType = requestBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)!!
            }
            if (isPlaintext(buffer)) {
                builder.append(buffer.readString(charset))
                builder.append(
                    """
请求结束 --> END ${request.method()} (${requestBody.contentLength()}-byte body)"""
                )
            } else {
                builder.append(
                    """
请求结束 --> END ${request.method()} (binary ${requestBody.contentLength()}-byte body omitted)"""
                )
            }
        }
        val startNs = System.nanoTime()
        val response: Response
        response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            builder.append(
                """

    请求出错 ----> ${e.message}
    """.trimIndent()
            )
            Log.d(TAG, "请求信息如下:\n$builder")
            throw e
        }
        val tookMs =
            TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        val responseBody = response.body()
        val responseHeaders = response.request().headers()
        builder.append("\n请求头 ----> \n$responseHeaders")
        if (requestBody != null) {
            val contentLength = responseBody!!.contentLength()
            builder.append("请求code ----> " + response.code() + " 用时:(" + tookMs + "ms)")
            val source = responseBody.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer
            var charset = UTF8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = try {
                    contentType.charset(UTF8)
                } catch (e: UnsupportedCharsetException) {
                    builder.append("\nCouldn't decode the response body; charset is likely malformed.")
                    builder.append("\n<-- END HTTP")
                    return response
                }
            }
            if (!isPlaintext(buffer)) {
                builder.append(
                    """

    <-- END HTTP (binary ${buffer.size()}-byte body omitted)
    """.trimIndent()
                )
                return response
            }
            if (contentLength != 0L) {
                builder.append("\n返回数据 ---->")
                builder.append(
                    """

    ${buffer.clone().readString(charset)}
    """.trimIndent()
                )
            }
            builder.append(
                """

    <-- 请求结束 END HTTP (${buffer.size()}-byte body)
    """.trimIndent()
            )
        }
        Log.d(TAG, "请求信息如下:\n$builder")
        return response
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    @Throws(EOFException::class)
    private fun isPlaintext(buffer: Buffer): Boolean {
        return try {
            val prefix = Buffer()
            val byteCount = if (buffer.size() < 64) buffer.size() else 64
            buffer.copyTo(prefix, 0, byteCount)
            for (i in 0..15) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(
                        codePoint
                    )
                ) {
                    return false
                }
            }
            true
        } catch (e: EOFException) {
            false // Truncated UTF-8 sequence.
        }
    }

    private fun bodyEncoded(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"]
        return contentEncoding != null && !contentEncoding.equals("identity", ignoreCase = true)
    }

    companion object {
        private const val TAG = "okhttp"
        private val UTF8 = Charset.forName("UTF-8")
    }
}