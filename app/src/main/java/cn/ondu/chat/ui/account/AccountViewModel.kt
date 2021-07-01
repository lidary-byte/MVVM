package cn.ondu.chat.ui.account

import androidx.lifecycle.MutableLiveData
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.api.BasicCallback
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.chat.bean.JStatus

class AccountViewModel : BaseViewModel() {


    val loginStatus by lazy { MutableLiveData<JStatus>() }
    fun login(account: String, pwd: String) {
        loginStatus.value = JStatus.LoadingStatus()
        JMessageClient.login(account,pwd,object : BasicCallback(){
            override fun gotResult(p0: Int, p1: String?) {
                loginStatus.value = JStatus.EndStatus(p0,p1)
            }
        })
    }


    val registerStatus by lazy { MutableLiveData<JStatus>() }
    fun register(account: String, pwd: String){
        registerStatus.value = JStatus.LoadingStatus()
        JMessageClient.register(account,pwd,object : BasicCallback(){
            override fun gotResult(p0: Int, p1: String?) {
                registerStatus.value = JStatus.EndStatus(p0,p1)
            }
        })
    }
}