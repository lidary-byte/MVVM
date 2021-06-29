package cn.ondu.gitee.ui.main.adapter

import cn.ondu.basecommon.base.BaseBindingQuickAdapter
import cn.ondu.gitee.bean.FromTypeListBean
import cn.ondu.gitee.databinding.ItemVideoListBinding
import coil.load

class HomeDetailsAdapter :
    BaseBindingQuickAdapter<FromTypeListBean, ItemVideoListBinding>(ItemVideoListBinding::inflate) {
    override fun convert(holder: BaseBindingHolder, item: FromTypeListBean) {
        holder.viewBinding<ItemVideoListBinding>().apply {
            ivImg.load(item.cover)
            tvActors.text = item.actors
            tvIntroduction.text = item.introduction
            tvRegion.text = item.region
            tvTitle.text = item.name
        }
    }
}