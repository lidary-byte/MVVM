package cn.ondu.basecommontest.module.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.base.BaseFragmentPagerAdapter
import cn.ondu.basecommontest.R
import cn.ondu.basecommontest.databinding.ActivityMainBinding
import cn.ondu.basecommontest.module.found.FoundFragment
import cn.ondu.basecommontest.module.person.PersonFragment
import com.jaeger.library.StatusBarUtil

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel by viewModels<MainViewModel>()

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.color_ffffff))
        StatusBarUtil.setDarkMode(this)
        val titles = resources.getStringArray(R.array.home_tabs)
        val fragments = listOf(PersonFragment(),FoundFragment())
        mViewBinding.viewPager.run {
            mViewBinding.viewPager.adapter = BaseFragmentPagerAdapter(supportFragmentManager,fragments,titles)
            mViewBinding.tabLayout.setupWithViewPager(this)
        }

    }

    override fun liveDataListener() {

    }

    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}
