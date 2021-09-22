plugins {
    id("com.android.application")
    kotlin("android")
}

//fun getDate(): String {
//    val date = Date()
//    return date.format("MMddhhmm")
//}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        applicationId = "cn.ondu.mvvm"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1 // project.properties['APP_CODE'].toInteger()
        versionName = "1.0.0" //APP_VERSION
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                ("proguard-rules.pro")
            )
        }
    }

    kotlinOptions.jvmTarget = "1.8"
    viewBinding.isEnabled = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
//    val kotlinVersion = ""
    implementation(fileTree("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.71")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation(project(":lib-common"))
    implementation(project(":lib-res"))
    implementation("com.scwang.smart:refresh-layout-kernel:2.0.3")      //核心必须依赖
    implementation("com.scwang.smart:refresh-header-classics:2.0.3")    //经典刷新头
    implementation("com.scwang.smart:refresh-footer-classics:2.0.3")    //经典加载
    implementation("io.coil-kt:coil:1.2.1")
    implementation("com.airbnb.android:lottie:3.7.0")
    implementation("com.tencent:mmkv-static:1.2.9")
    implementation("com.beloo.widget:ChipsLayoutManager:0.3.7@aar")
}
