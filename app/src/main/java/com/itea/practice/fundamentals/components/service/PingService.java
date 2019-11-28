package com.itea.practice.fundamentals.components.service;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.itea.practice.components.model.PingLog;
import com.itea.practice.components.service.PingServiceBase;

public class PingService extends PingServiceBase {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("temp_log", "bind");
        return new PingBinder(this);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("temp_log", "unbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("temp_log", "create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("temp_log", "start command");
        return START_NOT_STICKY;
    }

    @Override
    protected void onResult(PingLog result) {

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        this.onDestroy();
    }

    @Override
    public void onDestroy() {
        Log.d("temp_log", "destroy");
        interrupt();

        super.onDestroy();
    }

}
