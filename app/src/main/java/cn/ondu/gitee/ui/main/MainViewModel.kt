package cn.ondu.gitee.ui.main

import androidx.lifecycle.liveData
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.gitee.bean.AllTypeBean
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


    fun allTypeList() = liveData<List<AllTypeBean>>(Dispatchers.Main) {
        val typeList = withContext(Dispatchers.IO) {
            val data = mutableListOf<AllTypeBean>()
            data.add(AllTypeBean(1, "电影"))
            data.add(AllTypeBean(2, "连续剧"))
            data.add(AllTypeBean(3, "综艺"))
            data.add(AllTypeBean(4, "动漫"))
            data.add(AllTypeBean(5, "资讯"))
            data.add(AllTypeBean(6, "动作片"))
            data.add(AllTypeBean(7, "喜剧片"))
            data.add(AllTypeBean(8, "爱情片"))
            data.add(AllTypeBean(9, "科幻片"))
            data.add(AllTypeBean(10, "恐怖片"))
            data.add(AllTypeBean(11, "剧情片"))
            data.add(AllTypeBean(12, "战争剧"))
            data.add(AllTypeBean(13, "国产剧"))
            data.add(AllTypeBean(14, "香港剧"))
            data.add(AllTypeBean(15, "韩国剧"))
            data.add(AllTypeBean(16, "欧美剧"))
            data.add(AllTypeBean(17, "公告"))
            data.add(AllTypeBean(18, "头条"))
            data.add(AllTypeBean(21, "微电影"))
            data.add(AllTypeBean(22, "台湾剧"))
            data.add(AllTypeBean(23, "日本剧"))
            data.add(AllTypeBean(24, "海外剧"))
            data.add(AllTypeBean(25, "内地综艺"))
            data.add(AllTypeBean(26, "港台综艺"))
            data.add(AllTypeBean(27, "日韩综艺"))
            data.add(AllTypeBean(28, "欧美综艺"))
            data.add(AllTypeBean(29, "国产动漫"))
            data.add(AllTypeBean(30, "日韩动漫"))
            data.add(AllTypeBean(31, "欧美动漫"))
            data.add(AllTypeBean(32, "港台动漫"))
            data.add(AllTypeBean(33, "海外动漫"))
            data.add(AllTypeBean(34, "福利片"))
            data.add(AllTypeBean(35, "解说"))
            data.add(AllTypeBean(36, "电影解说"))
            data.add(AllTypeBean(37, "伦理片"))
            data
        }
        emit(typeList)
    }

    fun fromTypeData(type:Int,page:Int) = httpToLiveData {
        mRepo.fromTypeData(type,page)
    }
}