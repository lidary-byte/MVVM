package cn.ondu.basecommon.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import cn.ondu.basecommon.R
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.basecommon.util.dp
import cn.ondu.basecommon.util.singTapClick
import cn.ondu.basecommon.util.sp

/**
 * @author: lcc
 * @date: 2020/10/19
 * @GitHub: lidaryl@163.com
 * @email: lidaryl@163.com
 * @description: toolBar
 *
 */

open class CommonToolBar : RelativeLayout {

    private var title: String = ""

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.CommonToolBar)
        title = obtainStyledAttributes.getString(R.styleable.CommonToolBar_title)?:""
        obtainStyledAttributes.recycle()
        initView()
    }

    /**
     * 直接将view暴露出去反而是最方便的
     */
    val imageView by lazy { ImageView(context) }
    val textView by lazy { TextView(context) }
    val textViewRight by lazy { TextView(context) }

    private fun initView() {
        addView(imageView)
        addView(textView)
        addView(textViewRight)

        val imageLayoutParams = imageView.layoutParams as LayoutParams
        imageLayoutParams.width = 35.dp
        imageLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        imageView.layoutParams = imageLayoutParams
        imageView.setImageResource(R.drawable.ic_back)


        val textLayoutParams = textView.layoutParams as LayoutParams
        textLayoutParams.addRule(CENTER_IN_PARENT)
        textView.layoutParams = textLayoutParams
        textView.textSize = 18.sp.toFloat()
        textView.text = title

        val textRightLayoutParams = textViewRight.layoutParams as LayoutParams
        textRightLayoutParams.addRule(ALIGN_PARENT_END)
        textRightLayoutParams.addRule(CENTER_VERTICAL)
        textViewRight.layoutParams = textRightLayoutParams
        textView.textSize = 16.sp.toFloat()

        imageView.singTapClick {
            if (context is BaseActivity<*>) {
                (context as BaseActivity<*>).finish()
            } else if (context is BaseFragment<*>) {
                (context as BaseFragment<*>).requireActivity().finish()
            }
        }
        initOtherView()
    }


    protected open fun initOtherView() {}
}