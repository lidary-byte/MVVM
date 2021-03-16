package cn.ondu.basecommon.base

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ktx.immersionBar

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {


    protected val mLogTag = this::class.java.simpleName

    protected lateinit var mViewBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = viewBinding()
        setContentView(mViewBinding.root)
        initImmersionBar()
        initData(savedInstanceState)
        initView(savedInstanceState)
        viewListener()
        liveDataListener()
    }

    protected open fun initImmersionBar(){
        immersionBar {
            //自动状态栏
            autoDarkModeEnable(true)
            keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
            statusBarColor("#ffffff")
        }
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


    protected val mTag = this::class.java.simpleName

}