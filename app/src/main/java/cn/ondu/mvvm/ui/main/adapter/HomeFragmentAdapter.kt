package cn.ondu.mvvm.ui.main.adapter

import cn.jpush.im.android.api.model.Conversation
import cn.ondu.basecommon.base.BaseBindingQuickAdapter
import cn.ondu.mvvm.R
import cn.ondu.mvvm.databinding.ItemChatListBinding
import com.blankj.utilcode.util.TimeUtils

class HomeFragmentAdapter :
    BaseBindingQuickAdapter<Conversation, ItemChatListBinding>(ItemChatListBinding::inflate) {
    override fun convert(holder: BaseBindingHolder, item: Conversation) {
        holder.viewBinding<ItemChatListBinding>().apply {
            if (item.avatarFile != null) {
                ivImg.load(item.avatarFile)
            } else {
                ivImg.load(R.mipmap.ic_logo)
            }
            tvName.text = item.title
            tvTime.text =
                TimeUtils.millis2String(item.latestMessage.createTime, "yyyy-MM-dd HH:mm:ss")
            val asJsonObject = item.latestMessage.content.toJsonElement().asJsonObject
            tvMessage.text = asJsonObject.get("text").asString
        }
    }
}