package cn.ondu.basecommontest

import android.widget.Toast
import cn.ondu.basecommon.BaseActivity
import cn.ondu.basecommon.view.CommonToolBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun layoutResID(): Int = R.layout.activity_main


    override fun initView() {
        commToolBar.leftText = "返回键"
        commToolBar.leftTextSize = 18
        commToolBar.leftTextIcon = resources.getDrawable(R.mipmap.ic_launcher)

        commToolBar.centerText = "中"
        commToolBar.centerTextSize = 44
        commToolBar.centerTextIcon = resources.getDrawable(R.mipmap.ic_launcher)

        commToolBar.rightText = "哈哈"
        commToolBar.rightTextSize = 18
        commToolBar.rightTextIcon = resources.getDrawable(R.mipmap.ic_launcher)


        commToolBar.centerClickListener = {
            commToolBar.toolbarText("1", "2", "3")
        }
        commToolBar.rightClickListener = {
            Toast.makeText(
                this,
                "哈哈哈哈哈哈哈哈", Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun viewClickListener() {

    }

}
