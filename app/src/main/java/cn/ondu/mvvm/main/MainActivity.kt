package cn.ondu.mvvm.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.http.onError
import cn.ondu.basecommon.http.onFinish
import cn.ondu.basecommon.http.onStart
import cn.ondu.basecommon.http.onSuccess
import cn.ondu.basecommon.showViewHideOtherViews
import cn.ondu.basecommon.util.showToast
import cn.ondu.basecommon.util.singTapClick
import cn.ondu.mvvm.databinding.ActivityMainBinding
import com.blankj.utilcode.util.LogUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel by viewModels<MainViewModel>()


    override fun initView(savedInstanceState: Bundle?) {
        startHttp()
    }


    private fun startHttp() {
        mViewModel.articleList().observe(this, Observer {
            it.onStart {
                LogUtils.e("=======================", "网络请求中...")
//                showLoading()
                mViewBinding.flContent.showLoadingView()
            }
                .onSuccess {
                    LogUtils.e("=======================", "成功...${it?.size ?: 0}")
                    mViewBinding.tvContent.text = it?.datas?.get(0)?.toString() ?: ""
                    mViewBinding.flContent.showEmptyView()
                }
                .onError {code, message ->
                    LogUtils.e("=======================", "网络请求失败...$message")
                    showToast(message)
                    mViewBinding.flContent.showErrorView()
                }
                .onFinish {
                    LogUtils.e("=======================", "网络请求结束")
//                    mViewBinding.viewLoading.viewLoading.showViewHideOtherViews(mViewBinding.flContent)
//                    hideLoading()
                }
        })
    }

    override fun viewListener() {
        mViewBinding.tvStart.singTapClick { startHttp() }
//        mViewBinding.tvStartLoading.singTapClick{
//            mViewBinding.tvStartLoading
//        }
    }


    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}
