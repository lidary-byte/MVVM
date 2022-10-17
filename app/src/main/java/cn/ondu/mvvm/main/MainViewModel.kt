package cn.ondu.mvvm.main

import ArticleBean
import cn.ondu.basecommon.BaseViewModel
import cn.ondu.basecommon.http.MutableHttpLiveData

class MainViewModel : BaseViewModel() {

    private val mRepo by lazy { MainRepo() }

    val articleLiveData by lazy(LazyThreadSafetyMode.NONE) {
        MutableHttpLiveData<ArticleBean>()
    }

    init {
        articleList()
    }

    fun articleList() = httpToLiveData(articleLiveData) { mRepo.articleList() }
    fun articleListFlow() = mRepo.articleListFlow().httpToFlow()
}