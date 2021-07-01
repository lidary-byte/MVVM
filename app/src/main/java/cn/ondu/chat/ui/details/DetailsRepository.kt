package cn.ondu.chat.ui.details

import cn.ondu.basecommon.BaseRepository
import cn.ondu.basecommon.http.HttpClient
import cn.ondu.chat.http.ApiService

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
class DetailsRepository : BaseRepository() {
    suspend fun details(ids:String) = parsData { HttpClient.createApi(ApiService::class.java).videoDetails(ids) }

}