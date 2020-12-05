# BaseCommonMVVM

###  便于快速构建MVVM项目


## v1.0.1
```
新增ViewBinding
```

### v1.0
```
封装了Activity,Fragment,Retrofit,Log
```

使用方法:

1. 根项目的build.gradle里添加
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2.  model下的build.gradle中添加:
```
dependencies {
   implementation 'com.github.ohdu:BaseCommonMVVM:Tag'
	}
```
3. 在你的Application中调用
```
 BaseCommon.init(debug: Boolean, logTag: String = BaseCommon::class.java.simpleName)
```
