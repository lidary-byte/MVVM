package cn.ondu.basecommontest.ui.details

import androidx.lifecycle.liveData
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.basecommontest.bean.AllTypeBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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