package cn.ondu.mvvm.ui.account

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cn.ondu.basecommon.R
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.util.showToast
import cn.ondu.mvvm.bean.jMessageParsing
import cn.ondu.mvvm.databinding.ActivityRegisterBinding
import com.gyf.immersionbar.ktx.immersionBar

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val mViewModel by viewModels<AccountViewModel>()

    override fun viewListener() {
        mViewBinding.etAccount.addTextChangedListener {
            checkLoginBtnEnable()
        }
        mViewBinding.etPassWord.addTextChangedListener {
            checkLoginBtnEnable()
        }
        mViewBinding.tvRegister.singTapClick {
            mViewModel.register(
                mViewBinding.etAccount.text.toString(),
                mViewBinding.etPassWord.text.toString()
            )
        }

    }


    override fun liveDataListener() {
        mViewModel.loginStatus.observe(this, Observer {
            it.jMessageParsing(
                onLoading = { showLoading() },
                onFinish = { hideLoading() },
                onError = {
                    showToast(it)
                }) {
                val intent = Intent()
                intent.putExtra(LoginActivity.ACCOUNT, mViewBinding.etAccount.text.toString())
                intent.putExtra(LoginActivity.PASSWORD, mViewBinding.etPassWord.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })
    }
    override fun initImmersionBar() {
        immersionBar {
            //自动状态栏
            autoDarkModeEnable(true)
            fitsSystemWindows(true)
            keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
            statusBarColor(R.color.colorWhite)
        }
    }
    /**
     * 检查登陆按钮是否能被按下
     */
    private fun checkLoginBtnEnable() {
        mViewBinding.tvRegister.isEnabled =
            !mViewBinding.etAccount.text.isNullOrBlank() && !mViewBinding.etPassWord.text.isNullOrBlank()
    }

    override fun viewBinding(): ActivityRegisterBinding =
        ActivityRegisterBinding.inflate(layoutInflater)


}