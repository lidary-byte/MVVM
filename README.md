# MVVM框架

该框架便于快速搭建MVVM应用

- [x] 基于Retrofit+LiveData+协程的网络请求
- [x] 封装view布局状态切换
- [x] 带有ViewBinding的BaseActivity和BaseFragment
- [x] 带有懒加载的BaseFragment

~~[ ] 基于Flow的Bus框架(类似EventBus)~~

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

2.代码调用:

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





##  封装Retrofit+LiveData+协程用于网络请求

BaseBean需要实现IBaseBean接口

```kotlin
data class BaseBean<T>(val errorCode: Int, val errorMsg: String, val data: T) : IBaseBean<T> {
    override fun errorCode(): Int = errorCode
    override fun errorMsg(): String = errorMsg
    override fun data(): T = data
    override fun isSuccess(): Boolean  = errorCode == Config.HTTP_SUCCESS_CODE
}
```

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
                .onSuccess {data->}
                .onError { code, message -> }
                .onFinish {}
        })
    }
```

当然,你也可以

```kotlin
private fun startHttp() {
        mViewModel.articleList().observe(this, Observer {
             when(it){
                 is HttpStatus.LoadingStatus ->{}
                 is HttpStatus.SuccessStatus->{data->}
                 is HttpStatus.ErrorStatus->{code,message->}
                 is HttpStatus.FinishStatus->{}
             }
        })
    }
```

BaseRepository中仅对数据做脱壳处理

```kotlin
 suspend fun <T> parsData(
        block: suspend () -> IBaseBean<T>
    ): T {
        val httpResult = withContext(Dispatchers.IO) {
            block()
        }
        if (httpResult.isSuccess()) {
            return httpResult.data()
        } else {
            throw HttpException(httpResult.errorCode(), httpResult.errorMsg())
        }

    }
```

可根据业务需求适当扩展Error枚举类

```kotlin
enum class Error(private val code: Int, private val err: String) {
    UNKNOWN(1000, "请求失败，请稍后再试"),
    PARSE_ERROR(1001, "Json解析错误，请稍后再试"),
    NETWORK_ERROR(1002, "网络连接错误，请稍后重试"),
    SSL_ERROR(1004, "证书出错，请稍后再试"),
    TIMEOUT_ERROR(1006, "网络连接超时，请稍后重试");

    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}
```

BaseViewModel中进行具体的请求状态分发:

```kotlin
  protected fun <T> httpToLiveData(
        block: suspend () -> T
    ) = liveData<HttpStatus<T>>(Dispatchers.Main) {
        emit(HttpStatus.LoadingStatus())
        try {
            //repository里已经将异常抛出 这里直接捕获就行
            emit(HttpStatus.SuccessStatus(block()))
        } catch (error: Exception) {
            error.printStackTrace()
            val handleException = ExceptionHandle.handleException(error)
      emit(HttpStatus.ErrorStatus(handleException.errCode,handleException.errorMsg))
        } finally {
            emit(HttpStatus.FinishStatus())
        }
    }
```



3.封装BaseActivity,BaseFragment等基类(具体查看源码)

<font color=red>注:如果要在ViewPager里使用BaseFragment懒加载时FragmentPagerAdapter的behavior参数要传入BEHAVIOR_SET_USER_VISIBLE_HINT</font>

4.封装常用util工具(具体查看源码)
