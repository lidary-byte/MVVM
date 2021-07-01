package cn.ondu.chat.ui.main.fragment

import cn.ondu.basecommon.BaseRepository
import cn.ondu.basecommon.http.HttpClient
import cn.ondu.chat.http.ApiService

class PersonRepo : BaseRepository(){
    suspend fun login(account:String,pwd:String) =   HttpClient.createApi(ApiService::class.java).accountLogin(account,pwd)

}