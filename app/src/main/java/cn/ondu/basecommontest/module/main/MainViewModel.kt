package cn.ondu.basecommontest.module.main

import androidx.lifecycle.MutableLiveData
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.basecommon.http.HttpStatus
import cn.ondu.basecommontest.bean.MusicListBean

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
class MainViewModel : BaseViewModel(){
    private val mRepo by lazy { MainRepository() }
    val newSongState by lazy { MutableLiveData<HttpStatus<MusicListBean>>() }
    fun newSongList() = httpComplex<MusicListBean>(newSongState){
        val data = mRepo.newSongList()
        emit(data)
    }
}