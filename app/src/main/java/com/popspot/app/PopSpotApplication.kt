package com.popspot.app

import android.app.Application
import android.util.Log
import com.naver.maps.map.NaverMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PopSpotApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val naverMapKeyId = BuildConfig.NAVER_MAP_KEY_ID

        Log.d("NAVER_MAP_CHECK", "packageName=$packageName")
        Log.d("NAVER_MAP_CHECK", "naverMapKeyLength=${naverMapKeyId.length}")
        Log.d(
            "NAVER_MAP_CHECK",
            "naverMapKeyPreview=${
                if (naverMapKeyId.length >= 4) naverMapKeyId.take(4) + "****"
                else "EMPTY"
            }"
        )

        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NcpKeyClient(naverMapKeyId)
    }
}