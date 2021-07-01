package cn.ondu.chat.http

import cn.ondu.chat.MMKVConstant
import com.blankj.utilcode.util.LogUtils
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
        LogUtils.e("=================",MMKV.defaultMMKV()?.decodeString(MMKVConstant.KEY_TOKEN) ?: "")
        return chain.proceed(request)
    }
}