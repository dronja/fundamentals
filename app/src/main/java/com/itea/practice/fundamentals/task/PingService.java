package com.itea.practice.fundamentals.task;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.itea.practice.components.PingExecutor;
import com.itea.practice.components.PingLog;

//IntentService - one time service
public class PingService extends Service {
    String TAG = "pingService";
    private PingExecutor pingExecutor;
    private PingBinder pingBinder;
    private PingExecutor.CallBack callBack = new PingExecutor.CallBack() {
        @Override
        public void onSuccess(long started, long finished) {
            Log.d(TAG, "Success");
            if(pingBinder != null)
           pingBinder.onPing(new PingLog(true, finished-started,started));
        }

        @Override
        public void onFailure(long started, long finished) {
            Log.d(TAG, "Failure");
            if(pingBinder != null)
            pingBinder.onPing(new PingLog(false, finished-started, started));
        }
    };
    public PingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Bind");
        if(pingBinder == null){
            pingBinder = new PingBinder();
        }
       return pingBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Unbind");

        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Create");
        super.onCreate();
        pingExecutor = new PingExecutor();
        pingExecutor.execute(callBack, "8.8.8.8" );
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Destroy");
        super.onDestroy();
        pingExecutor.interrupt();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Start");
        return START_NOT_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(TAG, "Task remove");
        super.onTaskRemoved(rootIntent);
    }
}
