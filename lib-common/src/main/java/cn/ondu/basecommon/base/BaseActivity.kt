package cn.ondu.basecommon.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import cn.ondu.basecommon.CommApp
import cn.ondu.basecommon.R
import cn.ondu.basecommon.util.CommSharedViewModel
import cn.ondu.basecommon.view.LoadingDialog
import com.gyf.immersionbar.ImmersionBar


abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {


    protected val mLogTag = this::class.java.simpleName

    private var _viewBinding: T? = null
    protected val mViewBinding by lazy(LazyThreadSafetyMode.NONE) { _viewBinding!! }
    private val loadingDialog by lazy(LazyThreadSafetyMode.NONE) { LoadingDialog(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = viewBinding()
        setContentView(mViewBinding.root)
        initImmersionBar()
        initData(savedInstanceState)
        initView(savedInstanceState)
        viewListener()
        liveDataListener()
    }

    protected open fun initImmersionBar() {
        ImmersionBar.with(this)
            .autoDarkModeEnable(true)
            .fitsSystemWindows(true)
            .keyboardEnable(true)
            .statusBarColor(R.color.colorPrimary)
    }

    /**
     * 注册点击事件
     */
    protected open fun viewListener() {}

    /**
     * ViewModel 事件监听
     */
    protected open fun liveDataListener() {}
    protected open fun initView(savedInstanceState: Bundle?) {}
    protected open fun initData(savedInstanceState: Bundle?) {}
    protected abstract fun viewBinding(): T


    /**
     * 获取Application级别的ViewModel
     *
     */

    protected inline fun <reified SVM : CommSharedViewModel> applicationViewModel(activity: Activity): Lazy<SVM> {
        return ViewModelLazy(SVM::class,
            { (activity.applicationContext as CommApp).viewModelStore },
            { ViewModelProvider.AndroidViewModelFactory.getInstance(activity.application as CommApp) })
    }


    private var mFactory: ViewModelProvider.Factory? = null

    protected open fun getAppViewModelProvider(activity: Activity): ViewModelProvider {
        return ViewModelProvider(activity.applicationContext as CommApp, getAppFactory(activity))
    }

    private fun getAppFactory(activity: Activity): ViewModelProvider.Factory {

        val application: Application = checkApplication(activity)
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        }
        return mFactory!!
    }

    private fun checkApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException(
                "Your activity/fragment is not yet attached to "
                        + "Application. You can't request ViewModel before onCreate call."
            )
    }

    protected fun showLoading(text: String = "加载中,请稍等...") {
        loadingDialog.show(text)
    }

    protected fun hideLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
        super.onDestroy()

    }

    protected val mTag = this::class.java.simpleName

}