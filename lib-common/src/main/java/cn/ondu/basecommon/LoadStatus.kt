package cn.ondu.basecommon

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.forEach
import cn.ondu.basecommon.util.easyString


/**
 * View的切换(加载中布局,加载成功,加载失败...)
 * 调用该方法的对象为想要显示的View
 * @param parentView  ViewGroup通过外部传进来
 * 如果通过this.rootView as ViewGroup获取 菜单栏也会被隐藏
 */
fun View.showViewHideOtherViews(parentView:ViewGroup) {
    parentView.forEach {
        if (it==this){
            this.visibility = View.VISIBLE
        }else{
            this.visibility = View.GONE
        }
    }
}
