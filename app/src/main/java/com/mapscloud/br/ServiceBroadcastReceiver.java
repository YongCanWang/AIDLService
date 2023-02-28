package com.mapscloud.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author TomCan
 * @description:
 * @date:2023/2/28 10:39
 */
public class ServiceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "ServiceBroadcastReceiver";

    /**
     * 不可做耗时操作，会造成ANR
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: ");
        String arclient = intent.getStringExtra("arclient");
        if (arclient != null)
            Log.e(TAG, "onReceive: " + arclient);
    }
}
