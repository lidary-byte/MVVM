package cn.ondu.gitee.ui.details

import cn.ondu.basecommon.BaseViewModel

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
class DetailsViewModel : BaseViewModel() {
    private val mRepo by lazy { DetailsRepository() }



    fun videoDetails(ids:String) = httpToLiveData {
        mRepo.details(ids)
    }
}