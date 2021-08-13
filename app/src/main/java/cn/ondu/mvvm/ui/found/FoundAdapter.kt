package cn.ondu.mvvm.ui.found

import cn.ondu.mvvm.R
import cn.ondu.mvvm.bean.LoginPhoneBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */

class FoundAdapter : BaseQuickAdapter<LoginPhoneBean,BaseViewHolder>(R.layout.item_home){
    override fun convert(holder: BaseViewHolder, item: LoginPhoneBean) {
         holder.setText(R.id.tv_title,item.token)
    }

}