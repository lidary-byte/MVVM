package cn.ondu.basecommon.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.forEach
import cn.ondu.basecommon.R
import com.blankj.utilcode.util.LogUtils
import java.lang.IllegalArgumentException

/**
 * @author: lcc
 * @date: 2021/08/13
 * @GitHub: lidaryl@163.com
 * @email: lidaryl@163.com
 * @description: 不同状态下的布局显示
 *
 */

class StatusLayout : FrameLayout {

    private var viewLoadingLayoutResId = -1
    private var viewErrorLayoutResId = -1
    private var viewEmptyLayoutResId = -1

    private var viewLoading: View? = null
    private var viewContent: View? = null
    private var viewError: View? = null
    private var viewEmpty: View? = null

    private var defaultLoadStatus = LoadStatus.CONTENT.showType


    private var _loadStatus: Int? = null

    /**
     * 对外暴露当前状态
     */
    val loadStatus = _loadStatus
        get() {
            return field ?: defaultLoadStatus
        }


    private val mInflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.StatusLayout)
        viewLoadingLayoutResId =
            obtainStyledAttributes.getResourceId(R.styleable.StatusLayout_view_loading, -1)
        viewEmptyLayoutResId =
            obtainStyledAttributes.getResourceId(R.styleable.StatusLayout_view_empty, -1)
        viewErrorLayoutResId =
            obtainStyledAttributes.getResourceId(R.styleable.StatusLayout_view_error, -1)

        defaultLoadStatus = obtainStyledAttributes.getInt(
            R.styleable.StatusLayout_load_default,
            LoadStatus.CONTENT.showType
        )
        obtainStyledAttributes.recycle()
    }



    fun showLoadingView() {
        showViewStatus(LoadStatus.LOADING.showType)
    }

    fun showEmptyView() {
        showViewStatus(LoadStatus.EMPTY.showType)
    }

    fun showErrorView() {
        showViewStatus(LoadStatus.ERROR.showType)
    }

    fun showContentView() {
        showViewStatus(LoadStatus.CONTENT.showType)
    }


    private fun showViewStatus(showType: Int) {
        if (_loadStatus == showType) {
            return
        }
        when (showType) {
            LoadStatus.LOADING.showType -> {
                viewContent?.visibility = View.GONE
                viewEmpty?.visibility = View.GONE
                viewError?.visibility = View.GONE
                if (viewLoading == null) {
                    throw IllegalArgumentException("Loading布局不能为空")
                }
                viewLoading!!.visibility = View.VISIBLE
            }
            LoadStatus.CONTENT.showType -> {
                viewLoading?.visibility = View.GONE
                viewEmpty?.visibility = View.GONE
                viewError?.visibility = View.GONE
                if (viewContent == null) {
                    throw IllegalArgumentException("Content布局不能为空")
                }
                viewContent!!.visibility = View.VISIBLE
            }
            LoadStatus.ERROR.showType -> {
                viewContent?.visibility = View.GONE
                viewEmpty?.visibility = View.GONE
                viewLoading?.visibility = View.GONE
                if (viewError == null) {
                    throw IllegalArgumentException("ERROR布局不能为空")
                }
                viewError!!.visibility = View.VISIBLE
            }
            LoadStatus.EMPTY.showType -> {
                viewContent?.visibility = View.GONE
                viewLoading?.visibility = View.GONE
                viewError?.visibility = View.GONE
                if (viewEmpty == null) {
                    throw IllegalArgumentException("EMPTY布局不能为空")
                }
                viewEmpty!!.visibility = View.VISIBLE
            }
        }
        _loadStatus = showType
    }


    private fun createView() {
        if (viewLoadingLayoutResId != -1) {
            viewLoading = inflateView(viewLoadingLayoutResId)
            addView(viewLoading)
        }
        if (viewErrorLayoutResId != -1) {
            viewError = inflateView(viewErrorLayoutResId)
            addView(viewError)
        }
        if (viewEmptyLayoutResId != -1) {
            viewEmpty = inflateView(viewEmptyLayoutResId)
            addView(viewEmpty)
        }
    }


    private fun inflateView(layoutId: Int): View {
        return mInflater.inflate(layoutId, null)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount != 1) {
            throw IllegalArgumentException("StatusLayout初始化时有且只能有一个子布局")
        }
        viewContent = getChildAt(0)
        createView()
        showViewStatus(defaultLoadStatus)
    }

    enum class LoadStatus(val showType: Int) {
        LOADING(0),
        ERROR(1),
        EMPTY(2),
        CONTENT(3)
    }

}