package cn.ondu.mvvm.main

import cn.ondu.basecommon.BaseRepository
import cn.ondu.basecommon.http.HttpClient
import cn.ondu.mvvm.http.Api

class MainRepo : BaseRepository() {
    suspend fun articleList() = parsData { HttpClient.createApi(Api::class.java).articleList() }
}