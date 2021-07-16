package cn.ondu.chat.ui.main.fragment

import androidx.lifecycle.MutableLiveData
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.api.BasicCallback
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.chat.bean.JStatus

class PersonViewModel : BaseViewModel() {


//    val loginStatus by lazy { MutableLiveData<JStatus>() }
//    fun login(account: String, pwd: String) {
//        loginStatus.value = JStatus.LoadingStatus()
//        JMessageClient.login(account,pwd,object : BasicCallback(){
//            override fun gotResult(p0: Int, p1: String?) {
//                loginStatus.value = JStatus.EndStatus(p0,p1)
//            }
//        })
//
//    }
}