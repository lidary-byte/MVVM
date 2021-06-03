package cn.ondu.basecommontest

import cn.ondu.basecommontest.bean.AllTypeListBean
import cn.ondu.basecommontest.bean.KBaseBean
import cn.ondu.basecommontest.bean.LoginPhoneBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
interface ApiService  {
    @GET("api/resource/list")
    suspend fun allTypeList(@Query("page") page:Int = 1): KBaseBean<List<AllTypeListBean>>

}