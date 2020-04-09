package cn.ondu.basecommon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResID())
        initData(savedInstanceState)
        initView()

        viewClickListener()
        viewModelListener()

    }

    protected fun viewClickListener() {}
    protected fun viewModelListener() {}
    protected fun initView() {}
    protected fun initData(savedInstanceState: Bundle?) {}
    protected abstract fun layoutResID(): Int
}