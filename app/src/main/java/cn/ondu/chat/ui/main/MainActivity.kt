package cn.ondu.chat.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.model.UserInfo
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.util.singTapClick
import cn.ondu.chat.R
import cn.ondu.chat.databinding.ActivityMainBinding
import cn.ondu.chat.ui.account.LoginActivity
import cn.ondu.chat.ui.main.fragment.HomeFragment
import cn.ondu.chat.ui.main.fragment.PersonFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mFragments by lazy {
        listOf(
            HomeFragment(),
            PersonFragment()
        )
    }

    private val mBottomText by lazy { mutableListOf<TextView>() }
    private val mBottomImage by lazy { mutableListOf<ImageView>() }
    override fun initView(savedInstanceState: Bundle?) {
        mBottomText.add(mViewBinding.tvWeixin)
        mBottomText.add(mViewBinding.tvContactList)
        mBottomText.add(mViewBinding.tvFind)
        mBottomText.add(mViewBinding.tvProfile)
        mBottomImage.add(mViewBinding.ivWeixin)
        mBottomImage.add(mViewBinding.ibContactList)
        mBottomImage.add(mViewBinding.ibFind)
        mBottomImage.add(mViewBinding.ibProfile)


        //默认选中第一个
        changeFragment(0)
    }


    private val mViewModel by viewModels<MainViewModel>()
    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        val userInfo = JMessageClient.getMyInfo()
        if (userInfo==null){
            //用户未进行登录
            startActivity(Intent(this,LoginActivity::class.java))
        }
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
        checkBottomTab(clickIndex, lastClickIndex)
        lastClickIndex = clickIndex
        beginTransaction.commit()
    }

    override fun viewListener() {
//        mViewBinding.rgBottom.setOnCheckedChangeListener { radioGroup, i ->
//            when (i) {
//                R.id.rg_video -> changeFragment(0)
//                R.id.rg_person -> changeFragment(1)
//                else -> throw Exception("首页RadioButton切换异常")
//            }
//        }
        mViewBinding.reWeixin.singTapClick { changeFragment(0) }
        mViewBinding.reContactList.singTapClick { changeFragment(1) }
        mViewBinding.reFind.singTapClick { changeFragment(2) }
        mViewBinding.reProfile.singTapClick { changeFragment(3) }
    }

    override fun liveDataListener() {

    }


    private fun checkBottomTab(checkIndex: Int, lastIndex: Int) {
        if (checkIndex < mBottomImage.size && checkIndex >= 0) {
            mBottomText[checkIndex].isSelected = true
            mBottomImage[checkIndex].isSelected = true
        }
        if (lastIndex < mBottomImage.size && lastIndex >= 0) {
            mBottomText[lastIndex].isSelected = false
            mBottomImage[lastIndex].isSelected = false
        }

    }

    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}
