package cn.ondu.chat.ui.account

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import cn.ondu.basecommon.R
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.util.showToast
import cn.ondu.basecommon.util.singTapClick
import cn.ondu.chat.bean.jMessageParsing
import cn.ondu.chat.databinding.ActivityLoginBinding
import com.gyf.immersionbar.ktx.immersionBar

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val mViewModel by viewModels<AccountViewModel>()


    override fun viewListener() {
        mViewBinding.etAccount.addTextChangedListener {
            checkLoginBtnEnable()
        }
        mViewBinding.etPassWord.addTextChangedListener {
            checkLoginBtnEnable()
        }
        mViewBinding.tvLogin.singTapClick {
            mViewModel.login(
                mViewBinding.etAccount.text.toString(),
                mViewBinding.etPassWord.text.toString()
            )
        }
        mViewBinding.tvRegister.singTapClick {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, REGISTER_CODE)
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
                finish()
            }
        })
    }

    /**
     * 检查登陆按钮是否能被按下
     */
    private fun checkLoginBtnEnable() {
        mViewBinding.tvLogin.isEnabled =
            !mViewBinding.etAccount.text.isNullOrBlank() && !mViewBinding.etPassWord.text.isNullOrBlank()
    }

    override fun viewBinding(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let { data ->
            mViewModel.login(data.getStringExtra(ACCOUNT)!!, data.getStringExtra(PASSWORD)!!)
        }
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
    companion object {
        const val ACCOUNT = "account"
        const val PASSWORD = "pwd"
        const val REGISTER_CODE = 1
    }
}