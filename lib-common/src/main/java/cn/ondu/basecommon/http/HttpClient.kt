package cn.ondu.basecommon.http

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {
    /**
     * 返回okHttp对象
     */
    var retrofitInterface: RetrofitInterface? = null
        get() {
            if (field == null) {
                throw NullPointerException("请实现RetrofitInterface接口")
            }
            return field
        }

    fun okHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.SECONDS)    //连接超时 5s
            .readTimeout(5, TimeUnit.SECONDS)  //读取超时
            .writeTimeout(5, TimeUnit.SECONDS).apply {
                if (retrofitInterface!!.isPrintLog()) {
                    addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                        override fun log(message: String) {
                            Log.v("okHttp", message)
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                }
            }
    }


    var okHttpClient: OkHttpClient? = null
        get() {
            if (field == null) {
                return okHttpBuilder().build()
            }
            return field
        }

    fun retrofitObj(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(retrofitInterface!!.baseUrl())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient!!)
            .build()
    }

    /**
     *
     * @param service 接口
     */
    fun <T> createApi(service: Class<T>): T = retrofitObj().create(service)

}