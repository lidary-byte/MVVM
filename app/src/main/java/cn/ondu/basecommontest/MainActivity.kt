package cn.ondu.basecommontest

import android.widget.Toast
import cn.ondu.basecommon.BaseActivity
import cn.ondu.basecommontest.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun initView() {

        mViewBinding.commToolBar.leftText = "返回键"
        mViewBinding.commToolBar.leftTextSize = 18
        mViewBinding.commToolBar.leftTextIcon = resources.getDrawable(R.mipmap.ic_launcher)

        mViewBinding.commToolBar.centerText = "中"
        mViewBinding.commToolBar.centerTextSize = 44
        mViewBinding.commToolBar.centerTextIcon = resources.getDrawable(R.mipmap.ic_launcher)

        mViewBinding.commToolBar.rightText = "哈哈"
        mViewBinding.commToolBar.rightTextSize = 18
        mViewBinding.commToolBar.rightTextIcon = resources.getDrawable(R.mipmap.ic_launcher)


        mViewBinding.commToolBar.centerClickListener = {
            mViewBinding.commToolBar.toolbarText("1", "2", "3")
        }
        mViewBinding.commToolBar.rightClickListener = {
            Toast.makeText(
                this,
                "哈哈哈哈哈哈哈哈", Toast.LENGTH_SHORT
            ).show()
        }
    }
}
