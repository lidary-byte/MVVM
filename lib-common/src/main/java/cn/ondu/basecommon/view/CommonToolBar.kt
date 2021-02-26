package cn.ondu.basecommon.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import cn.ondu.basecommon.R
import cn.ondu.basecommon.databinding.LayoutToolbarBinding

/**
 * @author: lcc
 * @date: 2020/10/19
 * @GitHub: lidaryl@163.com
 * @email: lidaryl@163.com
 * @description: toolBar
 *
 */

class CommonToolBar : RelativeLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    /**
     * 直接将view暴露出去反而是最方便的
     */
    val mToolBarViewBinding: LayoutToolbarBinding? = null
        get() = field!!

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this)
        LayoutToolbarBinding.bind(view)
    }
}