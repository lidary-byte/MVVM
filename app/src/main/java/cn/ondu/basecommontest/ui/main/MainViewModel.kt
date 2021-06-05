package cn.ondu.basecommontest.ui.main

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
class MainViewModel : BaseViewModel() {
    private val mRepo by lazy { MainRepository() }

    fun token() = httpToLiveData { mRepo.token() }

    fun allTypeList() = liveData<List<AllTypeBean>>(Dispatchers.Main) {
        val typeList = withContext(Dispatchers.IO) {
            val data = mutableListOf<AllTypeBean>()
            data.add(AllTypeBean(1, "电影"))
            data.add(AllTypeBean(2, "电视剧"))
            data.add(AllTypeBean(3, "综艺"))
            data.add(AllTypeBean(4, "动漫"))
            data
        }
        emit(typeList)
    }

    fun fromTypeData(type:Int,rank:String,page:Int) = httpToLiveData {
        mRepo.fromTypeData(type,rank,page)
    }
}