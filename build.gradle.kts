// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlinVersion = "1.3.71"
    repositories {
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
        maven("https://jitpack.io")
        mavenCentral()
        google()
    }
}

tasks.register<Delete>(name = "clean"){
    group = "build"
    delete(rootProject.buildDir)
}
