package com.mapscloud.aidlservice.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;

import androidx.core.content.ContextCompat;

/**
 * @author TomCan
 * @description:
 * @date:2023/2/23 10:42
 */
public class PermissionsUtils {
    private static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    /**
     * 检查权限
     */
    public static void checkPermissions(Activity context) {
        PackageManager packageManager = context.getPackageManager();
        for (String s : permissions) {
            try {
                PermissionInfo permissionInfo = packageManager.getPermissionInfo(s, 0);
                CharSequence charSequence = permissionInfo.loadLabel(packageManager);
                if (ContextCompat.checkSelfPermission(context, s) != PackageManager.PERMISSION_GRANTED) {
                    context.requestPermissions(permissions, 10001);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
