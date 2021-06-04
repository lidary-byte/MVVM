package cn.ondu.basecommontest.module.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.base.BaseFragmentPagerAdapter
import cn.ondu.basecommon.http.httpStatusParsing
import cn.ondu.basecommon.util.CommSharedViewModel
import cn.ondu.basecommon.util.singTapClick
import cn.ondu.basecommontest.R
import cn.ondu.basecommontest.SharedViewModel
import cn.ondu.basecommontest.databinding.ActivityMainBinding
import cn.ondu.basecommontest.module.LoginActivity
import cn.ondu.basecommontest.module.found.FoundFragment
import cn.ondu.basecommontest.module.person.PersonFragment
import com.blankj.utilcode.util.LogUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel by viewModels<MainViewModel>()
    private val sharedViewModel by  applicationViewModel<SharedViewModel>(this)

    override fun initView(savedInstanceState: Bundle?) {
//        StatusBarUtil.setDarkMode(this)
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.color_ffffff),0)

        val titles = resources.getStringArray(R.array.home_tabs)
        val fragments = listOf(PersonFragment(), FoundFragment())
//        mViewBinding.viewPager.run {
//            mViewBinding.viewPager.adapter =
//                BaseFragmentPagerAdapter(supportFragmentManager, fragments, titles)
//            mViewBinding.tabLayout.setupWithViewPager(this)
//        }

        mViewModel.allType()

        mViewBinding.btnTest.singTapClick { startActivity(Intent(this,LoginActivity::class.java)) }
        mViewModel.allTypeState.observe(this, Observer {
//            it.httpStatusParsing({
//                LogUtil.e("=====","加载中")
//            },{
//                LogUtil.e("=====","加载失败$it")
//            },{
//                LogUtil.e("=====","加载完成")
//            }){
//                LogUtil.e("=====","加载成功${it?.size}")
//            }
        })
//        mViewModel.loginPhone().observe(this, Observer {
//
//        })
        sharedViewModel.mMoment.observeInActivity(this, Observer {
            LogUtils.e("=====================",it)
        })
    }

    override fun liveDataListener() {

    }


    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}
