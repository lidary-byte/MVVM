package cn.ondu.chat.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.basecommon.http.httpStatusParsing
import cn.ondu.basecommon.showContentView
import cn.ondu.basecommon.showErrorView
import cn.ondu.basecommon.showLoadingView
import cn.ondu.chat.databinding.FragmentHomeDetailsBinding
import cn.ondu.chat.ui.details.ChatDetailsActivity
import cn.ondu.chat.ui.ChatViewModel
import cn.ondu.chat.ui.main.adapter.HomeFragmentAdapter

class HomeDetailsFragment : BaseFragment<FragmentHomeDetailsBinding>() {

    private val mType by lazy { arguments?.getInt("type") ?: 1 }

    private var page = 1
    private val mViewModel by activityViewModels<ChatViewModel>()

    private val mAdapter by lazy { HomeFragmentAdapter() }

    /**
     * 根据type加载数据
     */
    private fun loadDataFromType() {
        mViewModel.fromTypeData(mType,page).observe(viewLifecycleOwner, Observer {
            it.httpStatusParsing({ mViewBinding.recyclerView.showLoadingView() }, {
                mViewBinding.recyclerView.showErrorView(it)
            }) {
                mViewBinding.recyclerView.showContentView()
            }
        })
    }


    override fun viewListener() {
        super.viewListener()
        mAdapter.setOnItemClickListener { adapter, view, position ->
            ChatDetailsActivity.start(requireContext(),mAdapter.data[position].id)
        }
    }
    override fun onLazyAfterView() {
        super.onLazyAfterView()
        loadDataFromType()
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mViewBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mViewBinding.recyclerView.adapter = mAdapter
    }

    companion object {
        fun instance(type: Int): HomeDetailsFragment {
            val homeDetailsFragment =
                HomeDetailsFragment()
            val bundle = Bundle()
            bundle.putInt("type", type)
            homeDetailsFragment.arguments = bundle
            return homeDetailsFragment
        }

    }

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeDetailsBinding = FragmentHomeDetailsBinding.inflate(inflater, container, false)
}