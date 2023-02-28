package com.mapscloud.aidlservice.aidl;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.mapscloud.ui.MainActivity;
import com.mapscloud.aidlservice.R;

/**
 * @author TomCan
 * @description:
 * @date:2023/2/22 16:24
 */
public class AIDLService extends Service {
    private static String TAG = "DataService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return new DataBeanImpl(); // 必须返回实质性(非空)对象，才会触发onServiceConnected方法
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: 传递过来的数据" + intent.getStringExtra("aidlclient"));
        Log.e(TAG, "onStartCommand");
        startForeground();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "onRebind");
        super.onRebind(intent);
    }



    private void startForeground() {
//        Notification.Builder builder = new Notification.Builder(this); //获取一个Notification构造器
//        Intent nfIntent = new Intent(this, MainActivity.class);
//        builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
//                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
//                .setContentTitle("下拉列表中的Title") // 设置下拉列表里的标题
//                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
//                .setContentText("要显示的内容") // 设置上下文内容
//                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

        /**
         *  参数1： targetSDK大于等于28时：
         *         设置为0报错ANR未在5s内调用startFroeground(),但是已经调用该方法
         *         设置为其他值，提示未注册android.permission.FOREGROUND_SERVICE权限，但是该权限已注册
         */
//        startForeground(0, builder.build());


        try {
            String ID = "com.mapscloud.aidlservice";    //这里的id里面输入自己的项目的包的路径
            String NAME = "aidl";
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            NotificationCompat.Builder notification = null; //创建服务对象
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(ID, NAME, manager.IMPORTANCE_HIGH);
                channel.enableLights(true);
                channel.setShowBadge(true);
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                manager.createNotificationChannel(channel);
                if (notification == null)
                    notification = new NotificationCompat.Builder(getApplicationContext()).setChannelId(ID);
            } else {
                if (notification == null)
                    notification = new NotificationCompat.Builder(getApplicationContext());
            }
            if (notification != null) {
                notification.setContentTitle("AIDLService")
                        .setContentText("我是AIDLService，我被AIDLClient调起了")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
                        .build();
                Notification notification1 = notification.build();
                startForeground(110, notification1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "启动通知错误：" + e);
        }

    }
}
