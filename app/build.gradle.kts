plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.vidhyaniketan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vidhyaniketan"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.cardview)
    implementation(libs.android.gif.drawable)
    implementation(libs.constraintlayout)
    implementation (libs.recyclerview)
    implementation(libs.material.v150)
    implementation(libs.viewpager2)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}