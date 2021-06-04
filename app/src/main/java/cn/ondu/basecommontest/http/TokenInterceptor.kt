package cn.ondu.basecommontest.http

import cn.ondu.basecommontest.MMKVConstant
import com.tencent.mmkv.MMKV
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                MMKVConstant.KEY_TOKEN,
                MMKV.defaultMMKV()?.decodeString(MMKVConstant.KEY_TOKEN) ?: ""
            )
            .build()

        return chain.proceed(request)

    }

}