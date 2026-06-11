import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    id("com.google.gms.google-services")
}

// local.properties 파일에서 API 키와 서명 정보를 읽기
val localProperties = Properties().also { props ->
    rootProject.file("local.properties")
        .takeIf { it.exists() }
        ?.inputStream()
        ?.use { props.load(it) }
}

fun localOrGradleProperty(name: String): String =
    localProperties.getProperty(name)
        ?: project.findProperty(name) as? String
        ?: ""

val releaseKeyStoreFile = listOf(
    project.file("popspot-release-key.jks"),
    rootProject.file("popspot-release-key.jks")
).firstOrNull { it.exists() } ?: project.file("popspot-release-key.jks")

val releaseStorePassword = localOrGradleProperty("RELEASE_STORE_PASSWORD")
val releaseKeyPassword = localOrGradleProperty("RELEASE_KEY_PASSWORD")
val releaseKeyAlias = localOrGradleProperty("RELEASE_KEY_ALIAS").ifBlank { "popspot-alias" }

val hasReleaseSigning = releaseKeyStoreFile.exists() &&
        releaseStorePassword.isNotBlank() &&
        releaseKeyPassword.isNotBlank()

android {
    namespace = "com.popspot.app"
    compileSdk = 35

    signingConfigs {
        create("release") {
            storeFile = releaseKeyStoreFile
            storePassword = releaseStorePassword
            keyPassword = releaseKeyPassword
            keyAlias = releaseKeyAlias
        }
    }

    defaultConfig {
        applicationId = "com.popspot.app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val naverClientId = localProperties.getProperty("naver.client.id", "")
        val naverClientSecret = localProperties.getProperty("naver.client.secret", "")
        val tourApiKey = localProperties.getProperty("tour.api.key", "")

        val naverMapKeyId = localProperties.getProperty("naver.map.client.id")
            ?: localProperties.getProperty("NAVER_MAP_KEY_ID")
            ?: ""

        val naverMapClientSecret = localProperties.getProperty("naver.map.client.secret")
            ?: localProperties.getProperty("NAVER_MAP_CLIENT_SECRET")
            ?: ""

        buildConfigField("String", "NAVER_MAP_KEY_ID", "\"$naverMapKeyId\"")
        buildConfigField("String", "NAVER_MAP_CLIENT_SECRET", "\"$naverMapClientSecret\"")

        buildConfigField("String", "NAVER_CLIENT_ID", "\"$naverClientId\"")
        buildConfigField("String", "NAVER_CLIENT_SECRET", "\"$naverClientSecret\"")
        buildConfigField("String", "TOUR_API_KEY", "\"$tourApiKey\"")
    }

    buildTypes {
        getByName("debug") {
            if (hasReleaseSigning) {
                signingConfig = signingConfigs.getByName("release")
            }
        }

        release {
            isMinifyEnabled = false

            if (hasReleaseSigning) {
                signingConfig = signingConfigs.getByName("release")
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(libs.androidx.lifecycle.runtime.compose)
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
    implementation(libs.androidx.compose.ui)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)

    // Naver Map
    implementation("com.naver.maps:map-sdk:3.23.2")


    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // 위치 권한 처리
    implementation(libs.accompanist.permissions)

    // 현재 위치 조회
    implementation(libs.play.services.location)

    // Coil 이미지 로딩
    implementation(libs.coil.compose)

    debugImplementation(libs.androidx.ui.tooling)

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
}