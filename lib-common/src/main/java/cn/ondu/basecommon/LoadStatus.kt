package cn.ondu.basecommon

import android.view.View
import android.widget.TextView
import cn.ondu.basecommon.util.easyString


object LoadStatus {
    var emptyViewId: Int? = null
    var errorViewId: Int? = null
    var errorTextId: Int? = null
    var loadingViewId: Int? = null
}

/**
 * 这几个方法是ContentView 的扩展方法
 * 因为加载失败,加载数据为空的viewId基本可以写死一个
 * 但是ContentViewId不一定(其实也可以写死 但是不太好)
 */
fun View.showContentView() {
    val rootView = this.rootView
    LoadStatus.loadingViewId?.let {
        rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.emptyViewId?.let {
        rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.errorViewId?.let {
        rootView.findViewById<View>(it).visibility = View.GONE
    }
    this.visibility = View.VISIBLE
}

fun View.showLoadingView() {
    val rootView = this.rootView
    LoadStatus.loadingViewId?.let {
        rootView.findViewById<View>(it).visibility = View.VISIBLE
    }
    LoadStatus.emptyViewId?.let {
        rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.errorViewId?.let {
        rootView.findViewById<View>(it).visibility = View.GONE
    }
    this.visibility = View.GONE
}

fun View.showEmptyView() {
    val rootView = this.rootView
    LoadStatus.loadingViewId?.let {
        rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.emptyViewId?.let {
        rootView.findViewById<View>(it).visibility = View.VISIBLE
    }
    LoadStatus.errorViewId?.let {
        rootView.findViewById<View>(it).visibility = View.GONE
    }
    this.visibility = View.GONE

}

fun View.showErrorView(errorText: String? = this.context.easyString(R.string.load_error)) {
    val rootView = this.rootView
    LoadStatus.loadingViewId?.let {
        rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.emptyViewId?.let {
        rootView.findViewById<View>(it).visibility = View.GONE
    }
    LoadStatus.errorViewId?.let {
        rootView.findViewById<View>(it).visibility = View.VISIBLE
    }

    LoadStatus.errorTextId?.let { this.rootView.findViewById<TextView>(it).text = errorText }

    this.visibility = View.GONE
}
