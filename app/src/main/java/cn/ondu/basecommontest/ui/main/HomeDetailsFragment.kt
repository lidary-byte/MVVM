package cn.ondu.basecommontest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.basecommontest.databinding.FragmentHomeDetailsBinding

class HomeDetailsFragment : BaseFragment<FragmentHomeDetailsBinding>(){
    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeDetailsBinding = FragmentHomeDetailsBinding.inflate(inflater,container,false)

}