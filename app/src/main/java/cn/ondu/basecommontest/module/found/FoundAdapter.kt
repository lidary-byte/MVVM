package cn.ondu.basecommontest.module.found

import cn.ondu.basecommontest.R
import cn.ondu.basecommontest.bean.InfoX
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */

class FoundAdapter : BaseQuickAdapter<InfoX,BaseViewHolder>(R.layout.item_home){
    override fun convert(holder: BaseViewHolder, item: InfoX) {
         holder.setText(R.id.tv_title,item.filename)
    }

}