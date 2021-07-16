package cn.ondu.chat.ui.main

import androidx.lifecycle.liveData
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.model.Conversation
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.chat.bean.AllTypeBean
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


    fun conversationList() = liveData<List<Conversation>> {
        emit(JMessageClient.getConversationList())
    }

    fun fromTypeData(type: Int, page: Int) = httpToLiveData {

    }
}