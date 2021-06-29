package cn.ondu.gitee.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.gitee.R
import cn.ondu.gitee.databinding.ActivityMainBinding
import cn.ondu.gitee.ui.main.fragment.HomeFragment
import cn.ondu.gitee.ui.main.fragment.PersonFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mFragments by lazy {
        listOf(
            HomeFragment(),
            PersonFragment()
        )
    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewBinding.rgBottom.check(R.id.rg_video)
        changeFragment(0)
    }

    private val mViewModel by viewModels<MainViewModel>()
    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
    }




    /**
     * 上一次选中的
     */
    private var lastClickIndex = -1

    /**
     * fragment切换
     * @param clickIndex 当前选中第几个
     */
    private fun changeFragment(clickIndex: Int) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        if (clickIndex == lastClickIndex) {
            return
        }
        if (lastClickIndex != -1) {
            val lastFragment = mFragments[lastClickIndex]
            beginTransaction.hide(lastFragment)
        }
        val fragment = mFragments[clickIndex]
        if (!fragment.isAdded) {
            beginTransaction.add(R.id.frame_layout, fragment, clickIndex.toString()).show(fragment)
        } else {
            if (fragment.isHidden) {
                beginTransaction.show(fragment)
            }
        }
        lastClickIndex = clickIndex
        beginTransaction.commit()
    }

    override fun viewListener() {
        mViewBinding.rgBottom.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rg_video -> changeFragment(0)
                R.id.rg_person -> changeFragment(1)
                else -> throw Exception("首页RadioButton切换异常")
            }
        }
    }

    override fun liveDataListener() {

    }


    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}
