package cn.ondu.basecommontest.module

import android.os.Bundle
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.util.singTapClick
import cn.ondu.basecommontest.SharedViewModel
import cn.ondu.basecommontest.databinding.ActivityMainBinding

class LoginActivity : BaseActivity<ActivityMainBinding>() {

    private val sharedViewModel by applicationViewModel<SharedViewModel>(this)

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mViewBinding.btnTest.singTapClick {
            sharedViewModel.mMoment.value = "阿斯兰的看法就阿斯顿联发"
        }
    }

    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

}