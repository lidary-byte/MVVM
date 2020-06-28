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

    protected open fun viewClickListener() {}
    protected open fun viewModelListener() {}
    protected open fun initView() {}
    protected open fun initData(savedInstanceState: Bundle?) {}
    protected abstract fun layoutResID(): Int
}