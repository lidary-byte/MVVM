package cn.ondu.mvvm.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.http.*
import cn.ondu.basecommon.showViewHideOtherViews
import cn.ondu.basecommon.util.showToast
import cn.ondu.basecommon.util.singTapClick
import cn.ondu.mvvm.databinding.ActivityMainBinding
import com.blankj.utilcode.util.LogUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel by viewModels<MainViewModel>()


    override fun initView(savedInstanceState: Bundle?) {
    }


    private fun startHttp() {
        mViewModel.articleList().observe(this, Observer {
            it.onStart { LogUtils.e("======onStart")}
                .onSuccess { data-> mViewBinding.tvContent.text = data?.toString()?:"" }
                .onError { code, message ->  LogUtils.e("======onError:$code----$message")}
                .onFinish { LogUtils.e("======onFinish")}
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
