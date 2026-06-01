import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    id("com.google.gms.google-services")
}

// local.properties 파일에서 API 키 읽기
val localProperties = Properties().also { props ->
    rootProject.file("local.properties")
        .takeIf { it.exists() }
        ?.inputStream()
        ?.use { props.load(it) }
}

android {
    namespace = "com.popspot.app"
    compileSdk = 34

    signingConfigs {
        create("release") {
            // 프로젝트 루트가 아니라 app 폴더 안에 있을 때
            storeFile = file("popspot-release-key.jks")
            storePassword = project.findProperty("RELEASE_STORE_PASSWORD") as? String ?: ""
            keyPassword = project.findProperty("RELEASE_KEY_PASSWORD") as? String ?: ""
            keyAlias = "popspot-alias"
        }
    }

    defaultConfig {
        applicationId = "com.popspot.app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "NAVER_CLIENT_ID", "\"${localProperties.getProperty("naver.client.id", "")}\"")
        buildConfigField("String", "NAVER_CLIENT_SECRET", "\"${localProperties.getProperty("naver.client.secret", "")}\"")
        buildConfigField("String", "PUBLIC_DATA_API_KEY", "\"${localProperties.getProperty("public.data.api.key", "")}\"")
        buildConfigField("String", "TOUR_API_KEY", "\"${localProperties.getProperty("tour.api.key", "")}\"")

        manifestPlaceholders["naverMapClientId"] = localProperties.getProperty("naver.client.id", "")
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("release")
        }
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    // ViewModel + Navigation
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)

    // Naver Map
    implementation(libs.naver.map.compose)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    debugImplementation(libs.androidx.ui.tooling)

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
}