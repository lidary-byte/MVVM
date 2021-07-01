package cn.ondu.chat.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import cn.ondu.basecommon.util.easyColor
import cn.ondu.chat.R

class EnableButton : AppCompatTextView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        setBackgroundResource(R.drawable.bg_btn_enable)
        setTextColor(context.easyColor(R.color.colorUnEnableText))
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (enabled) {
            setTextColor(context.easyColor(R.color.colorWhite))
        } else {
            setTextColor(context.easyColor(R.color.colorUnEnableText))
        }
    }


}