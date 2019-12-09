package com.itea.practice.fundamentals.task;

import android.os.Binder;

import com.itea.practice.components.PingLog;

public class PingBinder extends Binder {
    private PingListener pingListener;
    void onPing(PingLog pingLog){
        this.pingListener.onPing(pingLog);
    }
    void setOnPingListener(PingListener listener){
        this.pingListener = listener;
    }
    interface PingListener{
        void onPing(PingLog log);
    }
}
