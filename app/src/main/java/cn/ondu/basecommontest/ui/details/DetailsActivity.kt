package cn.ondu.basecommontest.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommontest.databinding.ActivityDetailsBinding

class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {

    private val ids by lazy { intent.getStringExtra("ids") }

    private val mViewModel by viewModels<DetailsViewModel>()

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        videoDetails()
    }

    private fun videoDetails() {
        mViewModel.videoDetails(ids).observe(this, Observer {

        })
    }

    override fun viewBinding(): ActivityDetailsBinding =
        ActivityDetailsBinding.inflate(layoutInflater)


    companion object {
        fun start(context: Context, ids: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("ids", ids)
            context.startActivity(intent)
        }
    }

}