package cn.ondu.basecommon.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {
    /**
     * 返回okHttp对象
     */
    private var retrofitInterface: RetrofitInterface? = null
        get() {
            if (field==null){
                throw NullPointerException("请实现RetrofitInterface接口")
            }
            return field
        }
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
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpObj())
            .build()
    }

    /**
     *
     * @param service 接口
     */
    fun <T> createApi(service: Class<T>): T = retrofitObj().create(service)

    /**
     * 给一个默认的
     */
    fun createApi() = createApi(ApiService::class.java)
}