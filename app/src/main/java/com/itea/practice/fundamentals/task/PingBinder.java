package com.itea.practice.fundamentals.task;

import android.os.Binder;

import androidx.annotation.CallSuper;

import com.itea.practice.components.PingLog;

abstract class PingBinder extends Binder {
    private PingListener pingListener;
    private ExecutionTypeListener typeListener;
    void onPing(PingLog pingLog){
        this.pingListener.onPing(pingLog);
    }
    void setOnPingListener(PingListener listener){
        this.pingListener = listener;
    }

    void setTypeListener(ExecutionTypeListener listener){
        this.typeListener = listener;
    }
    @CallSuper
    void  startPingProcess(){
        typeListener.onStatusChange(true);
    }
    @CallSuper
    void stopPingService(){
        typeListener.onStatusChange(false);
    }
    abstract boolean isStarted();

    interface ExecutionTypeListener{
        void onStatusChange(boolean state);
    }
    interface PingListener{
        void onPing(PingLog log);
    }

}
