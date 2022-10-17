package cn.ondu.mvvm.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.http.*
import cn.ondu.basecommon.util.singTapClick
import cn.ondu.mvvm.databinding.ActivityMainBinding
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.flow.*

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mViewModel by viewModels<MainViewModel>()


    override fun initView(savedInstanceState: Bundle?) {

    }


    private fun startHttpToFlow() {
        lifecycleScope.launchWhenStarted {
            mViewModel.articleListFlow().onHttpStart { LogUtils.e("======onStart") }
                .onHttpSuccess { data -> mViewBinding.tvContent.text = data?.toString() ?: "" }
                .onHttpError { code, message -> LogUtils.e("======onError:$code----$message") }
                .onHttpFinish { LogUtils.e("======onFinish") }
        }
    }


    override fun viewListener() {
        mViewBinding.tvStart.singTapClick { mViewModel.articleList() }
//        mViewBinding.tvStartLoading.singTapClick{
//            mViewBinding.tvStartLoading
//        }
    }

    override fun liveDataListener() {
        mViewModel.articleLiveData.observeStatus(this) {
            onStart { LogUtils.e("======onStart") }
            onSuccess { mViewBinding.tvContent.text = it.datas.toString() }
            onError { code, message -> mViewBinding.tvContent.text = message }
            onFinish { LogUtils.e("======onFinish") }
        }
    }

    override fun viewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}
