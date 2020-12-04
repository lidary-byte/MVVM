package cn.ondu.basecommon

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    protected val mViewBinding: T by lazy {
        //使用反射得到viewbinding的class
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(mViewBinding.root)
        initData(savedInstanceState)
        initView()
        registerViewClick()
        viewModelListener()
    }

    /**
     * 注册点击事件
     */
    protected open fun registerViewClick() {}

    /**
     * ViewModel 事件监听
     */
    protected open fun viewModelListener() {}
    protected open fun initView() {}
    protected open fun initData(savedInstanceState: Bundle?) {}
}