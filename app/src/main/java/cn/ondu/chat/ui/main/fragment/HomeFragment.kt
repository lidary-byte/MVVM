package cn.ondu.chat.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.chat.databinding.FragmentHomeBinding
import cn.ondu.chat.ui.ChatViewModel
import cn.ondu.chat.ui.details.ChatDetailsActivity
import cn.ondu.chat.ui.main.adapter.HomeFragmentAdapter
import com.blankj.utilcode.util.LogUtils

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val mViewModel by activityViewModels<ChatViewModel>()


    private val mChatListAdapter by lazy { HomeFragmentAdapter() }


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mViewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mChatListAdapter
        }
    }


    override fun viewListener() {
        mChatListAdapter.setOnItemClickListener { _, _, position ->
            startConversation(mChatListAdapter.data[position].latestMessage.fromUser.userName)
        }
    }

    /**
     * 创建聊天会话
     */
    private fun startConversation(userName: String) {
        ChatDetailsActivity.start(requireContext(),userName)
    }

    /**
     * 获取会话列表
     */
    private fun conversationList() {
        mViewModel.conversationList().observe(viewLifecycleOwner, Observer {
            mChatListAdapter.setList(it)
            LogUtils.v("消息列表:", it.toString())
        })
    }


    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        conversationList()
    }


    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)
}