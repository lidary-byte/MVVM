package cn.ondu.basecommontest.module.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.ondu.basecommon.base.BaseLazyFragment
import cn.ondu.basecommontest.bean.InfoX
import cn.ondu.basecommontest.databinding.FragmentFoundBinding
import cn.ondu.basecommontest.databinding.FragmentPersonBinding

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
        mViewModel.musicList().observe(viewLifecycleOwner, Observer {
            mAdapter.setList((it!!.list!!.list!!.info as MutableList<InfoX>?)!!)
        })
    }

    override fun tryLoadData1() {
        mViewModel.musicList().observe(viewLifecycleOwner, Observer {
            mAdapter.setList((it!!.list!!.list!!.info as MutableList<InfoX>?)!!)
        })
    }
    override fun loadData() {
        mViewModel.musicList().observe(viewLifecycleOwner, Observer {
            mAdapter.setList((it!!.list!!.list!!.info as MutableList<InfoX>?)!!)
        })
    }

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFoundBinding = FragmentFoundBinding.inflate(inflater, container, false)

}