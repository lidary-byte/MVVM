package cn.ondu.basecommontest

import cn.ondu.basecommontest.bean.LoginPhoneBean
import retrofit2.http.GET

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
interface ApiService  {
    @GET("login/cellphone")
    suspend fun loginPhone(): LoginPhoneBean
}