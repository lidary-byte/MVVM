package cn.ondu.chat.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.chat.databinding.FragmentHomeBinding
import cn.ondu.chat.ui.main.MainViewModel
import cn.ondu.chat.ui.main.adapter.HomeFragmentAdapter
import com.blankj.utilcode.util.LogUtils

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val mViewModel by activityViewModels<MainViewModel>()



    private val mChatListAdapter by lazy { HomeFragmentAdapter() }



    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mViewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mChatListAdapter
        }
    }


    /**
     * 获取会话列表
     */
    private fun conversationList() {
        mViewModel.conversationList().observe(viewLifecycleOwner, Observer {
            mChatListAdapter.setList(it)
            LogUtils.json("消息列表:",it[0].latestMessage)
        })
    }


    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        conversationList()
    }


    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)
}