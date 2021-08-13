package cn.ondu.mvvm.http

import ArticleBean
import cn.ondu.mvvm.bean.BaseBean
import retrofit2.http.GET

interface Api {
    @GET("article/list/0/json")
    suspend fun articleList(): BaseBean<ArticleBean>

}