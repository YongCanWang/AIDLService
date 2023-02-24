package com.mapscloud.aidlservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mapscloud.aidlservice.aidl.AIDLService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        start()
    }

    private fun start() {
        // 方式1
        val intent = Intent()
        intent.action =
            "com.mapscloud.aidlservice.aidl.dataservice.action" // AndroidManifest intent-filter
        intent.`package` = "com.mapscloud.aidlservice"  // applicationId

        // 方式2
        val intentService = Intent(this, AIDLService::class.java)


        startForegroundService(intentService)
        startService(intentService)
    }
}