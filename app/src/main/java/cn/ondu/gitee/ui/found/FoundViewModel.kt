package cn.ondu.gitee.ui.found

import androidx.lifecycle.MutableLiveData
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.basecommon.http.HttpStatus
import cn.ondu.gitee.bean.LoginPhoneBean

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
class FoundViewModel : BaseViewModel(){
    private val mRepo by lazy { FoundRepository() }
    val musicListStatus by lazy { MutableLiveData<HttpStatus<LoginPhoneBean>>() }
//    fun musicList() = httpComplex(musicListStatus){
//        val data = mRepo.musicList()
//        emit(data)
//    }
}