package cn.ondu.basecommontest.ui.main

import cn.ondu.basecommon.BaseRepository
import cn.ondu.basecommon.http.HttpClient
import cn.ondu.basecommontest.ApiService

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
class MainRepository : BaseRepository() {
    suspend fun fromTypeData() = parsData { HttpClient.createApi(ApiService::class.java).fromTypeData() }

}