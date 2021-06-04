package cn.ondu.basecommontest.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.basecommon.http.httpStatusParsing
import cn.ondu.basecommon.util.showContentView
import cn.ondu.basecommon.util.showLoadingView
import cn.ondu.basecommontest.databinding.FragmentHomeDetailsBinding
import cn.ondu.basecommontest.ui.main.MainViewModel
import cn.ondu.basecommontest.ui.main.adapter.HomeDetailsAdapter

class HomeDetailsFragment : BaseFragment<FragmentHomeDetailsBinding>() {

    private val mType by lazy { arguments?.getInt("type") ?: 1 }

    private var page = 1
    private val mViewModel by activityViewModels<MainViewModel>()

    private val mAdapter by lazy { HomeDetailsAdapter() }

    /**
     * 根据type加载数据
     */
    private fun loadDataFromType() {
        mViewModel.fromTypeData(mType, page).observe(viewLifecycleOwner, Observer {
            it.httpStatusParsing({mViewBinding.refreshLayout.showLoadingView()},{},{

            }) {
                mAdapter.setList(it)
                mViewBinding.refreshLayout.showContentView()
            }
        })
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