package cn.ondu.basecommon

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    protected open var mActivity: Activity? = null
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

    protected open fun initView() {}
    protected open fun initData(savedInstanceState: Bundle?) {}
    protected abstract fun resourceId(): Int
    protected open fun viewClickListener() {}
    protected open fun viewModelListener() {}
}