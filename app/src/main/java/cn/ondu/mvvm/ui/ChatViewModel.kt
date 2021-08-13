package cn.ondu.mvvm.ui

import androidx.lifecycle.liveData
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.model.Conversation
import cn.ondu.basecommon.BaseViewModel

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
class ChatViewModel : BaseViewModel() {


    /**
     * 获取会话列表
     */
    fun conversationList() = liveData<List<Conversation>> {
        emit(JMessageClient.getConversationList())
    }

    /**
     * 创建单聊
     */
    fun createConversation(userName: String) = liveData<Conversation> {
        Conversation.createSingleConversation(userName)
        emit(singleConversation(userName))
    }

    /**
     * 获取单个单聊会话
     */
    private fun singleConversation(userName: String) =
        JMessageClient.getSingleConversation(userName)


    fun fromTypeData(type: Int, page: Int) = httpToLiveData {

    }
}