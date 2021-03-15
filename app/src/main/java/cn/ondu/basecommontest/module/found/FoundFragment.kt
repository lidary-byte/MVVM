package cn.ondu.basecommontest.module.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.ondu.basecommon.base.BaseLazyFragment
import cn.ondu.basecommon.http.httpStatusParsing
import cn.ondu.basecommon.util.LogUtil
import cn.ondu.basecommontest.bean.InfoX
import cn.ondu.basecommontest.databinding.FragmentFoundBinding

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: 我的
 */
class FoundFragment : BaseLazyFragment<FragmentFoundBinding>() {

    private val mAdapter by lazy { FoundAdapter() }

    private val mViewModel by viewModels<FoundViewModel>()

    override fun initView(savedInstanceState: Bundle?) {
        mViewBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
        }
    }

    override fun tryLoadData() {
        mViewModel.musicList()

    }

    override fun liveDataListener() {
        mViewModel.musicListStatus.observe(viewLifecycleOwner, Observer {
            it.httpStatusParsing({ LogUtil.e("加载中") },
                { message ->  LogUtil.e("加载失败$message") },
                { LogUtil.e("加载完成") },
                { LogUtil.e("加载成功") })
        })
    }

    override fun tryLoadData1() {

    }

    override fun loadData() {

    }

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFoundBinding = FragmentFoundBinding.inflate(inflater, container, false)

}