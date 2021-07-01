package cn.ondu.basecommon.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import cn.ondu.basecommon.R
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.basecommon.util.*

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
        imageLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        imageLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        imageView.setPadding(2.px,10.px,2.px,10.px)
        imageView.layoutParams = imageLayoutParams
        imageView.setImageResource(R.drawable.ic_back)

        val textLayoutParams = textView.layoutParams as LayoutParams
        textLayoutParams.addRule(CENTER_IN_PARENT)
        textView.layoutParams = textLayoutParams
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
        textView.text = title
        textView.setTextColor(context.easyColor(R.color.colorBlack))

        val textRightLayoutParams = textViewRight.layoutParams as LayoutParams
        textRightLayoutParams.addRule(ALIGN_PARENT_END)
        textRightLayoutParams.addRule(CENTER_VERTICAL)
        textViewRight.layoutParams = textRightLayoutParams
        textViewRight.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)

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