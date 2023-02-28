package com.mapscloud.ui

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mapscloud.aidlservice.R
import com.mapscloud.aidlservice.aidl.AIDLService
import com.mapscloud.br.ServiceBroadcastReceiver

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        start()

        // 动态注册广播
        registerReceiver()
    }

    private fun registerReceiver() {
        // 动态注册广播
        val intentFilter = IntentFilter()
        // 动态注册自定义广播
        intentFilter.addAction("com.mapscloud.br.servicebroadcastreceiver.action")
        // 动态注册系统开关机广播
        intentFilter.addAction("android.intent.action.BOOT_COMPLETED")
        // 动态注册网络变化广播
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        val serviceBroadcastReceiver = ServiceBroadcastReceiver()
        registerReceiver(serviceBroadcastReceiver, intentFilter)
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