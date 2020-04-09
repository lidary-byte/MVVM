package cn.ondu.basecommon

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected var mActivity: Activity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as BaseActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(resourceId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData(savedInstanceState)
        initView()
        viewClickListener()
        viewModelListener()
    }

    protected fun initView() {}
    protected fun initData(savedInstanceState: Bundle?) {}
    protected abstract fun resourceId(): Int
    protected fun viewClickListener() {}
    protected fun viewModelListener() {}
}