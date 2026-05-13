import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

// Read API keys from local.properties (never commit real keys)
val localProperties = Properties().also { props ->
    rootProject.file("local.properties")
        .takeIf { it.exists() }
        ?.inputStream()
        ?.use { props.load(it) }
}

android {
    namespace = "com.popspot.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.popspot.app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String", "NAVER_CLIENT_ID",
            "\"${localProperties.getProperty("naver.client.id", "REPLACE_ME")}\""
        )
        buildConfigField(
            "String", "NAVER_CLIENT_SECRET",
            "\"${localProperties.getProperty("naver.client.secret", "REPLACE_ME")}\""
        )
        buildConfigField(
            "String", "PUBLIC_DATA_API_KEY",
            "\"${localProperties.getProperty("public.data.api.key", "REPLACE_ME")}\""
        )
        buildConfigField(
            "String", "TOUR_API_KEY",
            "\"${localProperties.getProperty("tour.api.key", "REPLACE_ME")}\""
        )
        manifestPlaceholders["naverMapClientId"] =
            localProperties.getProperty("naver.client.id", "REPLACE_ME")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

    // Compose BOM — pins all androidx.compose.* versions together
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

    // Naver Map Compose (transitively pulls in the Naver Map SDK)
    implementation(libs.naver.map.compose)

    // Room (scrap persistence)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    debugImplementation(libs.androidx.ui.tooling)
}
