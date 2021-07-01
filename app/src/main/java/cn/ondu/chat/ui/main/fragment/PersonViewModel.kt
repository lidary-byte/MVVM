package cn.ondu.chat.ui.main.fragment

import androidx.lifecycle.viewModelScope
import cn.ondu.basecommon.BaseViewModel
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PersonViewModel : BaseViewModel() {
    private val mRepo by lazy { PersonRepo() }
    fun login(account: String, pwd: String) {
        viewModelScope.launch(Dispatchers.Main) {
            mRepo.login(account, pwd)
                .flowOn(Dispatchers.IO)
                .catch {

                }
                .collect {
                    LogUtils.e("================",it.toString())
                }
        }

    }
}