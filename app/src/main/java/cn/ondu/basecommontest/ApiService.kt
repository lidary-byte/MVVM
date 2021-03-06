package cn.ondu.basecommontest

import cn.ondu.basecommontest.bean.MusicListBean
import retrofit2.http.GET

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
interface ApiService  {
    @GET("plist/list/125032?json=true")
    suspend fun musicList(): MusicListBean
}