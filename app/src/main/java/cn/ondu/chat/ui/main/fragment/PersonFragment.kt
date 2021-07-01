package cn.ondu.chat.ui.main.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import cn.ondu.basecommon.base.BaseFragment
import cn.ondu.basecommon.util.singTapClick
import cn.ondu.chat.databinding.FragmentPersonBinding

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: 我的
 */
class PersonFragment : BaseFragment<FragmentPersonBinding>() {

    private val mViewModel by viewModels<PersonViewModel>()

    override fun viewListener() {
        mViewBinding.etAccount.addTextChangedListener {
            checkLoginBtnEnable()
        }
        mViewBinding.etPassWord.addTextChangedListener {
            checkLoginBtnEnable()
        }
        mViewBinding.tvLogin.singTapClick {
            mViewModel.login(mViewBinding.etAccount.text.toString() ,mViewBinding.etPassWord.text.toString())
        }
    }


    /**
     * 检查登陆按钮是否能被按下
     */
    private fun checkLoginBtnEnable(){
        mViewBinding.tvLogin.isEnabled = !mViewBinding.etAccount.text.isNullOrBlank() && !mViewBinding.etPassWord.text.isNullOrBlank()
    }

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonBinding = FragmentPersonBinding.inflate(inflater, container, false)

}