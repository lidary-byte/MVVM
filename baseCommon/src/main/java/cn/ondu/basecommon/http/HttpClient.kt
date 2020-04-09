package cn.ondu.basecommon.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {

    /**
     * 返回okHttp对象
     */
    private fun okHttpObj(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)    //连接超时 5s
            .readTimeout(5, TimeUnit.SECONDS)  //读取超时
            .writeTimeout(5, TimeUnit.SECONDS)

//            .addNetworkInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> ALogUtils.longI(TAG,message) }).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private fun retrofitObj(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpObj())
            .build()
    }
}