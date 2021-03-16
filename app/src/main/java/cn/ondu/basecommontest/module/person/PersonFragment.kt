package cn.ondu.basecommontest.module.person

import android.view.LayoutInflater
import android.view.ViewGroup
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.basecommontest.databinding.FragmentPersonBinding

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: 我的
 */
class PersonFragment : BaseFragment<FragmentPersonBinding>() {
    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonBinding = FragmentPersonBinding.inflate(inflater, container, false)

}