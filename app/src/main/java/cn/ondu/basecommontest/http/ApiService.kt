package cn.ondu.basecommontest.http

import cn.ondu.basecommontest.bean.FromTypeListBean
import cn.ondu.basecommontest.bean.KBaseBean
import cn.ondu.basecommontest.bean.VideoDetailsBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
interface ApiService {
    @GET("api/resource/list")
    suspend fun fromTypeData(
        @Query("type") type: Int,
        @Query("page") page: Int = 1
    ): KBaseBean<List<FromTypeListBean>>

    @GET("api/detail")
    suspend fun videoDetails(@Query("ids") ids: String): KBaseBean<List<VideoDetailsBean>>
}