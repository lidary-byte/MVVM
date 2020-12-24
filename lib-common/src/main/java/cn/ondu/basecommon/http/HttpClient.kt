package cn.ondu.basecommon.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {

    /**
     * 返回okHttp对象
     */
    private val retrofitInterface: RetrofitInterface? = null
    private fun okHttpObj(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)    //连接超时 5s
            .readTimeout(5, TimeUnit.SECONDS)  //读取超时
            .writeTimeout(5, TimeUnit.SECONDS).apply {
                if (retrofitInterface!!.isPrintLog()) {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
            }.build()


    }

    fun retrofitObj(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(retrofitInterface!!.baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpObj())
            .build().apply {
                create(ApiService::class.java)
            }
    }
}