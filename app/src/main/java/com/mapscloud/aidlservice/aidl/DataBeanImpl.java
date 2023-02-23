package com.mapscloud.aidlservice.aidl;

import android.os.RemoteException;

import com.mapscloud.aidlservice.IMyAidlInterface;

/**
 * @author TomCan
 * @description: Binder 远程对象的基础接口，轻量级远程过程调用机制的核心部分，专为执行进程内和跨进程调用时的高性能而设计。该接口描述了与可远程对象交互的抽象协议。
 * 不要直接实现这个接口，而是从Binder扩展。
 * @date:2023/2/22 16:19
 */
public class DataBeanImpl extends IMyAidlInterface.Stub {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    /**
     * 具体业务
     *
     * @param code
     * @return
     */
    @Override
    public String getStr(int code) {
        String json = "null";
        switch (code) {
            case 1:
                json = "json:" + 1111;
                break;
            case 2:
                json = "json:" + 2222;
                break;
            case 3:
                json = "json:" + 3333;
                break;
            case 4:
                json = "json:" + 4444;
                break;
        }
        return json;
    }
}
