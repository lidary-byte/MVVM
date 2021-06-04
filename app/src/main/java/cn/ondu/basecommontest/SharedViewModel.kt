package cn.ondu.basecommontest

import cn.ondu.basecommon.util.CommSharedViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData


class SharedViewModel : CommSharedViewModel() {
    val mMoment: UnPeekLiveData<String> = UnPeekLiveData.Builder<String>()
        .setAllowNullValue(false)
        .create()
}