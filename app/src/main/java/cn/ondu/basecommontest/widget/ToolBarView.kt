package cn.ondu.basecommontest.widget

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import cn.ondu.basecommon.view.CommonToolBar
import cn.ondu.basecommontest.R

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
class ToolBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CommonToolBar(context, attrs, defStyleAttr) {

    override fun initOtherView() {
        mToolBarViewBinding.apply {
            toolBarRoot.setBackgroundColor(ContextCompat.getColor(context, R.color.color_1396F1))
            toolBarCenter.setTextColor(ContextCompat.getColor(context, R.color.color_ffffff))
        }
    }
}