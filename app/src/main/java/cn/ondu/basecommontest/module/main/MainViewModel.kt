package cn.ondu.basecommontest.module.main

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.basecommon.http.HttpStatus
import cn.ondu.basecommontest.Config
import cn.ondu.basecommontest.bean.LoginPhoneBean

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
class MainViewModel : BaseViewModel(){
    private val mRepo by lazy { MainRepository() }
    val loginPhoneStatus by lazy { MutableLiveData<HttpStatus<LoginPhoneBean>>() }
    fun loginPhone() = httpComplex(loginPhoneStatus){
        val data = mRepo.newSongList()
        if (data.code == Config.HTTP_SUCCESS_CODE){
            emit(data)
        }else{
            throw NetworkErrorException("网络请求异常,请稍候再试...")
        }
    }
}