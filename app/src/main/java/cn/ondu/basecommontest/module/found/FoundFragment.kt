package cn.ondu.basecommontest.module.found

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.basecommontest.databinding.FragmentFoundBinding

/**
 * @author: lcc
 * @date: 2021/3/16
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */

class FoundFragment : BaseFragment<FragmentFoundBinding>(){
    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFoundBinding  = FragmentFoundBinding.inflate(inflater,container,false)


}