package cn.ondu.mvvm.main

import cn.ondu.basecommon.BaseViewModel

class MainViewModel : BaseViewModel() {

    private val mRepo by lazy { MainRepo() }
    fun articleList() = httpToLiveData {  mRepo.articleList() }
    fun articleListFlow() = mRepo.articleListFlow().httpToFlow()
}