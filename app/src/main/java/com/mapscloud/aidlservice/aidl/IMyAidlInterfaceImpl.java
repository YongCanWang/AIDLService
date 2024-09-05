package com.mapscloud.aidlservice.aidl;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.mapscloud.aidlservice.IMyAidlInterface;
import com.mapscloud.call.CallbackInterface;

/**
 * @author TomCan
 * @description: Binder 远程对象的基础接口，轻量级远程过程调用机制的核心部分，专为执行进程内和跨进程调用时的高性能而设计。该接口描述了与可远程对象交互的抽象协议。
 * 不要直接实现这个接口，而是从Binder扩展。
 * @date:2023/2/22 16:19
 */
public class IMyAidlInterfaceImpl extends IMyAidlInterface.Stub {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    /**
     * 具体业务:两个数字相加
     * @param number1
     * @param number2
     * @return 两个数字之和
     * @throws RemoteException
     */
    @Override
    public String add(int number1, int number2) throws RemoteException {
        return String.valueOf(number1 + number2);
    }

    @Override
    public void setListener(int number1, int number2, CallbackInterface callbackInterface) throws RemoteException {
        Log.e("setListener", "number1:" + number1 +"---"+ "number2:" + number2);
        Bundle bundle = new Bundle(CallbackInterface.class.getClassLoader());
        bundle.putInt("number1", number1);
        bundle.putInt("number2", number2);
        bundle.putString("result", String.valueOf(number1 + number2));
        int hashCode = callbackInterface.hashCode();
        Log.e("setListener", "CallbackInterface hashCode: " + hashCode);
        callbackInterface.callback(number1, number2,  bundle);
    }

    @Override
    public void setCallbackListener(int number1, int number2, IBinder callbackInterface) throws RemoteException {
        Bundle bundle = new Bundle(CallbackInterface.class.getClassLoader());
        bundle.putInt("number1", number1);
        bundle.putInt("number2", number2);
        bundle.putString("result", String.valueOf(number1 + number2));
        int hashCode = callbackInterface.hashCode();
        Log.e("setCallbackListener", "CallbackInterfaceIBinder hashCode: " + hashCode);
        CallbackInterface callbackInterfaceInterface = CallbackInterface.Stub.asInterface(callbackInterface);
        Log.e("setCallbackListener", "callbackInterfaceInterface hashCode: " + callbackInterfaceInterface.hashCode());
        callbackInterfaceInterface.callback(number1, number2,  bundle);
    }
}
