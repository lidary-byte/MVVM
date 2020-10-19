package cn.ondu.basecommon.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.ondu.basecommon.R
import cn.ondu.basecommon.util.ScreenUtil
import kotlinx.android.synthetic.main.layout_toolbar.view.*

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
     * 设置字体
     */
    private var _leftText: String = "左边"

    var leftText: String
        get() = _leftText
        set(value) {
            _leftText = value
            tvLeft.text = value
        }

    private var _rightText: String = "左边"

    var rightText: String
        get() = _rightText
        set(value) {
            _rightText = value
            tvRight.text = value
        }

    private var _centerText: String = "左边"

    var centerText: String
        get() = _centerText
        set(value) {
            _centerText = value
            tvCenter.text = value
        }


    /**
     * 设置字体大小
     */
    private var _leftTextSize = ScreenUtil.dip2px(context, 16f)
    var leftTextSize: Int
        get() = _leftTextSize
        set(value) {
            _leftTextSize = value
            tvLeft.textSize = value.toFloat()
        }

    private var _centerTextSize = ScreenUtil.dip2px(context, 18f)
    var centerTextSize: Int
        get() = _centerTextSize
        set(value) {
            _centerTextSize = value
            tvCenter.textSize = value.toFloat()
        }


    private var _rightTextSize = ScreenUtil.dip2px(context, 16f)
    var rightTextSize: Int
        get() = _rightTextSize
        set(value) {
            _rightTextSize = value
            tvRight.textSize = value.toFloat()
        }


    /**
     * 设置对应图片
     */
    private var _leftTextIcon: Drawable? = context.getDrawable(R.drawable.ic_back)
    var leftTextIcon: Drawable?
        get() = _leftTextIcon
        set(value) {
            _leftTextIcon = value
            tvLeft.setDrawables(value, null, null, null)
        }

    private var _centerTextIcon: Drawable? = null
    var centerTextIcon: Drawable?
        get() = _centerTextIcon
        set(value) {
            _centerTextIcon = value
            tvCenter.setDrawables(value, null, null, null)
        }


    private var _rightTextIcon: Drawable? = null
    var rightTextIcon: Drawable?
        get() = _rightTextIcon
        set(value) {
            _rightTextIcon = value
            tvRight.setDrawables(null, null, null, value)
        }


    private var _leftTextColor = Color.BLACK
    var leftTextColor: Int
        get() = _leftTextSize
        set(value) {
            _leftTextSize = value
            tvLeft.setTextColor(value)
        }


    private var _rightTextColor = Color.BLACK
    var rightTextColor: Int
        get() = _rightTextSize
        set(value) {
            _rightTextSize = value
            tvLeft.setTextColor(value)
        }


    private var _centerTextColor = Color.BLACK
    var centerTextColor: Int
        get() = _centerTextColor
        set(value) {
            _centerTextColor = value
            tvLeft.setTextColor(value)
        }


    init {
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this)
        tvLeft.setOnClickListener {
            if (::leftClickListener.isInitialized) {
                leftClickListener()
            } else {
                (context as AppCompatActivity).finish()
            }
        }
        tvCenter.setOnClickListener {
            if (::centerClickListener.isInitialized) {
                centerClickListener()
            }
        }
        tvRight.setOnClickListener {
            if (::rightClickListener.isInitialized) {
                rightClickListener()
            }
        }
    }


    private fun TextView.setDrawables(
        drawableLeft: Drawable? = null,
        drawableTop: Drawable? = null,
        drawableRight: Drawable? = null,
        drawableBottom: Drawable? = null
    ) {

        drawableLeft?.setBounds(0, 0, drawableLeft.minimumWidth, drawableLeft.minimumHeight)
        drawableRight?.setBounds(0, 0, drawableRight.minimumWidth, drawableRight.minimumHeight)
        drawableTop?.setBounds(0, 0, drawableTop.minimumWidth, drawableTop.minimumHeight)
        drawableBottom?.setBounds(0, 0, drawableBottom.minimumWidth, drawableBottom.minimumHeight)
        this.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom)
    }


    fun toolbarText(leftText: String, centerText: String, rightText: String) {
        this.leftText = leftText
        this.centerText = centerText
        this.rightText = rightText
    }


    lateinit var leftClickListener: () -> Unit
    lateinit var centerClickListener: () -> Unit
    lateinit var rightClickListener: () -> Unit

}