package cn.ondu.chat.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import cn.ondu.basecommon.base.BaseActivity
import cn.ondu.basecommon.http.httpStatusParsing
import cn.ondu.chat.databinding.ActivityDetailsBinding
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager

class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {

    private val ids by lazy { intent.getStringExtra("ids") }

    private val mViewModel by viewModels<DetailsViewModel>()

    private val mAdapter by lazy { DetailsAdapter() }


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mViewBinding.recyclerViewNumber.layoutManager = ChipsLayoutManager.newBuilder(this).build()
        mViewBinding.recyclerViewNumber.adapter = mAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        videoDetails()
    }


    private fun videoDetails() {

    }

    override fun viewListener() {
        super.viewListener()
        mAdapter.setOnItemClickListener { adapter, view, position ->
            if (position == mAdapter.watchIndex) {
                return@setOnItemClickListener
            }
            mAdapter.watchIndex = position
            watchIndex(
                mAdapter.data[mAdapter.watchIndex].url,
                mAdapter.data[mAdapter.watchIndex].name
            )
        }
    }

    /**
     * 设置当前观看第几集
     */
    private fun watchIndex(url: String, name: String) {
        mViewBinding.detailPlayer.setUpLazy(
            url,
            true,
            null,
            null,
            name
        )
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