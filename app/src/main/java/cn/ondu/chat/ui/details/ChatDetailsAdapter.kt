package cn.ondu.chat.ui.details

import cn.jpush.im.android.api.model.Message
import cn.ondu.basecommon.base.BaseBindingQuickAdapter
import cn.ondu.chat.databinding.ItemChatDetailsListBinding

class ChatDetailsAdapter :
    BaseBindingQuickAdapter<Message, ItemChatDetailsListBinding>(ItemChatDetailsListBinding::inflate) {


    override fun convert(holder: BaseBindingHolder, item: Message) {
        holder.viewBinding<ItemChatDetailsListBinding>().apply {
            val messageContent = item.content.toJsonElement().asJsonObject
            tvText.text = messageContent.get("text").asString
        }
    }
}