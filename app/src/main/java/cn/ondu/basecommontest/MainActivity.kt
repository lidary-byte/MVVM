package cn.ondu.basecommontest

import cn.ondu.basecommon.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun layoutResID(): Int = R.layout.activity_main


    override fun initView() {
        setSupportActionBar(toolbar_main)
    }

    override fun viewClickListener() {

    }

}
