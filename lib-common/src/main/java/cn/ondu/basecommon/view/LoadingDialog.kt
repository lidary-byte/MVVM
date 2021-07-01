package cn.ondu.basecommon.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import cn.ondu.basecommon.R
import cn.ondu.basecommon.databinding.DialogLoadingBinding

/**
 * 转圈圈，等待
 * 关于LoadingDialog: 并没有做单例模式(如果一个页面多个请求关闭时无法确定)
 * 而是每个页面new一个且count==0，显示时 count+=1  而后依次关闭
 * @author lcc
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.loadingDialogStyle) {
    private val anim: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.loading) }

    private val mViewBinding by lazy { DialogLoadingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        setCanceledOnTouchOutside(false)
        //去掉这句话，背景会变暗
        window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }


    fun show(text: String) {
        if (!this.isShowing) {
            mViewBinding.tvLoadText.text = text
            show()
        }
    }


    override fun dismiss() {
        super.dismiss()
        if (this.isShowing) {
            mViewBinding.dialogIvLoading.clearAnimation()
        }
    }

    override fun show() {
        super.show()
        mViewBinding.dialogIvLoading.startAnimation(anim)
    }

}
