plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        val release by getting {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
    implementation(fileTree("dir" to "libs", "include" to arrayOf("*.jar")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.71")
    api("androidx.core:core-ktx:1.3.2")
    api("androidx.appcompat:appcompat:1.2.0")
    api("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    api("androidx.fragment:fragment-ktx:1.2.0")
    // okhttp
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    api("com.squareup.okhttp3:logging-interceptor:4.9.0")
    //retrofit
    api("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.5.0")

    //Moshi
    api("com.squareup.moshi:moshi:1.8.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")

    //activity
    api("androidx.activity:activity-ktx:1.2.0")

    //room
    val room_version = "2.2.6"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    api("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    //https://github.com/CymChad/BaseRecyclerViewAdapterHelper
    api("com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4")
    // https://github.com/gyf-dev/ImmersionBar
    api("com.gyf.immersionbar:immersionbar:3.0.0")
    api("com.gyf.immersionbar:immersionbar-components:3.0.0")
    // if u use AndroidX, use the following
    api("com.blankj:utilcodex:1.30.6")
//    implementation project(path: ':lib-res')
    //https://github.com/KunMinX/UnPeek-LiveData
    api("com.kunminx.archi:unpeek-livedata:4.5.0-beta1")
}
