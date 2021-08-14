# MVVM框架

改框架便于快速搭建MVVM应用



## 封装StatusLayout用于布局状态切换

使用:

1.xml中定义:

```xml
<!--StatusLayout初始化时有且只能有一个子View-->
    <cn.ondu.basecommon.view.StatusLayout
        android:id="@+id/fl_content"
        app:view_loading="@layout/view_loading"
        app:view_empty="@layout/view_empty"
        app:view_error="@layout/view_error"
        app:load_default="content"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

<!--内容布局-->
        <LinearLayout
            android:id="@+id/ll_content"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"/>
    </cn.ondu.basecommon.view.StatusLayout>
```

​	2.代码调用:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.flStatus.showContentView()
        mViewBinding.flStatus.showEmptyView()
        mViewBinding.flStatus.showErrorView()
        mViewBinding.flStatus.showLoadingView()
        //获取当前状态
        mViewBinding.flStatus.loadStatus
    }
```

tag:当前状态分为：

```kotlin
enum class LoadStatus(val showType: Int) {
        LOADING(0),
        ERROR(1),
        EMPTY(2),
        CONTENT(3)
    }
```





2.封装Retrofit+LiveData+协程用于网络请求

ViewModel,Repository分别继承BaseViewModel和BaseRepository

Repository中

```kotlin
class MainRepo : BaseRepository() {
    suspend fun articleList() = parsData {
    HttpClient.createApi(Api::class.java).articleList() }
}
```

ViewModel中

```kotlin
class MainViewModel : BaseViewModel() {

    private val mRepo by lazy { MainRepo() }
    fun articleList() = httpToLiveData {  mRepo.articleList() }
}
```

Activity中

```kotlin
private val mViewModel by viewModels<MainViewModel>()

 private fun startHttp() {
        mViewModel.articleList().observe(this, Observer {
            it.onStart {}
                .onSuccess {}
                .onError { code, message -> }
                .onFinish {}
        })
    }
```

当然,你也可以

```kotlin
private val mViewModel by viewModels<MainViewModel>()

 private fun startHttp() {
        mViewModel.articleList().observe(this, Observer {
             when(it){
                 is HttpStatus.LoadingStatus ->{}
                 is HttpStatus.SuccessStatus->{}
                 is HttpStatus.ErrorStatus->{}
                 is HttpStatus.FinishStatus->{}
             }
        })
    }
```



3.封装BaseActivity,BaseFragment等基类(具体查看源码)

4.封装常用util工具(具体查看源码)