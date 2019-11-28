package com.itea.practice.fundamentals.components.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.itea.practice.components.data.PingExecutor;

public class PingService extends Service {
    private final PingExecutor executor = new PingExecutor();
    private final PingCallback callback = new PingCallback();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("temp_log", "create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.executor.execute(this.callback, "8.8.8.8");

        return START_NOT_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        this.onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("temp_log", "destroy");
        executor.interrupt();
    }

    private final class PingCallback implements PingExecutor.CallBack {
        @Override
        public void onSuccess(long started, long finished) {
            Log.d("temp_log", "success");
        }

        @Override
        public void onFailure(long started, long finished) {
            Log.d("temp_log", "error");
        }
    }

}
