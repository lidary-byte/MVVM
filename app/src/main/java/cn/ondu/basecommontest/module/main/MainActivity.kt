package cn.ondu.basecommontest.module.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.base.BaseFragmentPagerAdapter
import cn.ondu.basecommon.http.httpStatusParsing
import cn.ondu.basecommon.util.LogUtil
import cn.ondu.basecommontest.R
import cn.ondu.basecommontest.databinding.ActivityMainBinding
import cn.ondu.basecommontest.module.found.FoundFragment
import cn.ondu.basecommontest.module.person.PersonFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel by viewModels<MainViewModel>()

    override fun initView(savedInstanceState: Bundle?) {
//        StatusBarUtil.setDarkMode(this)
//        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.color_ffffff),0)

        val titles = resources.getStringArray(R.array.home_tabs)
        val fragments = listOf(PersonFragment(), FoundFragment())
        mViewBinding.viewPager.run {
            mViewBinding.viewPager.adapter =
                BaseFragmentPagerAdapter(supportFragmentManager, fragments, titles)
            mViewBinding.tabLayout.setupWithViewPager(this)
        }

        mViewModel.allType()

        mViewModel.allTypeState.observe(this, Observer {
            it.httpStatusParsing({
                LogUtil.e("=====","加载中")
            },{
                LogUtil.e("=====","加载失败$it")
            },{
                LogUtil.e("=====","加载完成")
            }){
                LogUtil.e("=====","加载成功${it?.size}")
            }
        })
//        mViewModel.loginPhone().observe(this, Observer {
//
//        })
    }

    override fun liveDataListener() {

    }


    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}
