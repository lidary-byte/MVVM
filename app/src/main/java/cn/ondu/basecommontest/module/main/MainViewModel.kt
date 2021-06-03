package cn.ondu.basecommontest.module.main

import androidx.lifecycle.MutableLiveData
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.basecommon.http.HttpStatus
import cn.ondu.basecommontest.bean.AllTypeListBean

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
class MainViewModel : BaseViewModel(){
    private val mRepo by lazy { MainRepository() }
    fun loginPhone() = httpToLiveData {
        mRepo.allTypeList()
    }

    val allTypeState by lazy { MutableLiveData<HttpStatus<List<AllTypeListBean>>>() }
    fun allType() {
        http(allTypeState){
            mRepo.allTypeList()
        }
    }
}