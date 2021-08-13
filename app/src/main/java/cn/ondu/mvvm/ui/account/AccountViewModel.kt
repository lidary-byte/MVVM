package cn.ondu.mvvm.ui.account

import androidx.lifecycle.MutableLiveData
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.callback.GetUserInfoCallback
import cn.jpush.im.android.api.callback.RequestCallback
import cn.jpush.im.android.api.model.DeviceInfo
import cn.jpush.im.android.api.model.UserInfo
import cn.jpush.im.api.BasicCallback
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.mvvm.JPushKey
import cn.ondu.mvvm.bean.JStatus

class AccountViewModel : BaseViewModel() {


    val loginStatus by lazy { MutableLiveData<JStatus<UserInfo>>() }
    fun login(account: String, pwd: String) {
        loginStatus.postValue(JStatus.LoadingStatus())
        JMessageClient.login(account, pwd, object : RequestCallback<List<DeviceInfo>>() {
            override fun gotResult(p0: Int, p1: String?, p2: List<DeviceInfo>?) {
                //登录成功获取用户信息
                userInfo(account)
            }

        })
    }


    fun userInfo(account: String) {
        JMessageClient.getUserInfo(account, JPushKey.APP_KEY, object : GetUserInfoCallback() {
            override fun gotResult(p0: Int, p1: String?, p2: UserInfo?) {
                loginStatus.postValue(JStatus.EndStatus(p0, p1, p2))
            }
        })
    }

    val registerStatus by lazy { MutableLiveData<JStatus<Any>>() }
    fun register(account: String, pwd: String) {
        registerStatus.value = JStatus.LoadingStatus()
        JMessageClient.register(account, pwd, object : BasicCallback() {
            override fun gotResult(p0: Int, p1: String?) {
                registerStatus.value = JStatus.EndStatus(p0, p1)
            }
        })
    }
}