package cn.ondu.basecommontest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.basecommon.base.BaseFragmentPagerAdapter
import cn.ondu.basecommontest.bean.AllTypeBean
import cn.ondu.basecommontest.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val mViewModel by activityViewModels<MainViewModel>()

    private val mTitleDataString by lazy { mutableListOf<String>() }

    private val mFragments by lazy { mutableListOf<Fragment>() }

    private var mTitleData: List<AllTypeBean>? = null

    private val viewPagerAdapter by lazy {
        BaseFragmentPagerAdapter(
            childFragmentManager, mFragments, mTitleDataString,
            FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT
        )
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mViewBinding.viewPager.adapter = viewPagerAdapter
        mViewBinding.tabLayout.setupWithViewPager(mViewBinding.viewPager)
    }


    /**
     * 获取tab数据
     */
    private fun loadAllType() {
        mViewModel.allTypeList().observe(viewLifecycleOwner, Observer {
            it.forEach {
                mTitleDataString.add(it.title)
                mFragments.add(HomeDetailsFragment())
            }
            this.mTitleData = it
            viewPagerAdapter.notifyDataSetChanged()
        })
    }


    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        loadAllType()
    }


    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)
}