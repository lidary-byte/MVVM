package cn.ondu.mvvm.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.mvvm.databinding.ActivityChatDetailsBinding
import cn.ondu.mvvm.ui.ChatViewModel

class ChatDetailsActivity : BaseActivity<ActivityChatDetailsBinding>() {

    private val userName by lazy { intent.getStringExtra("userName") }

    private val mViewModel by viewModels<ChatViewModel>()

    private val mAdapter by lazy { ChatDetailsAdapter() }


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mViewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mViewBinding.recyclerView.adapter = mAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        createConversation(userName)
    }

    /**
     * 创建聊天会话并且获取聊天信息
     */
    private fun createConversation(userName: String) {
        mViewModel.createConversation(userName).observe(this, Observer {
            mAdapter.setList(it.allMessage)
        })
    }


    override fun viewBinding(): ActivityChatDetailsBinding =
        ActivityChatDetailsBinding.inflate(layoutInflater)


    companion object {
        fun start(context: Context, userName: String) {
            val intent = Intent(context, ChatDetailsActivity::class.java)
            intent.putExtra("userName", userName)
            context.startActivity(intent)
        }
    }

}