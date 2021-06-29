package cn.ondu.gitee.http

import cn.ondu.gitee.bean.FromTypeListBean
import cn.ondu.gitee.bean.KBaseBean
import cn.ondu.gitee.bean.VideoDetailsBean
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