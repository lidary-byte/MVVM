package cn.ondu.chat.http

import bean.AccountLoginBean
import cn.ondu.chat.GiteeInfo
import cn.ondu.chat.bean.FromTypeListBean
import cn.ondu.chat.bean.KBaseBean
import cn.ondu.chat.bean.VideoDetailsBean
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

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


    @POST("oauth/token")
    @FormUrlEncoded
    suspend fun accountLogin(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("user_info") userInfo:String = "projects pull_requests issues notes keys hook groups gists enterprises",
        @Field("grant_type") grantType:String = "password",
        @Field("client_id") clientId: String = GiteeInfo.CLIENT_ID,
        @Field("client_secret") clientSecret: String = GiteeInfo.CLIENT_SECRET
    ): Flow<AccountLoginBean>
}