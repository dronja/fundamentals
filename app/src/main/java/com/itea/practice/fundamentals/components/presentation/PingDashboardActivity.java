package com.itea.practice.fundamentals.components.presentation;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.itea.practice.components.presentation.PingDashboardActivityBase;
import com.itea.practice.components.R;
import com.itea.practice.fundamentals.components.service.PingService;

public class PingDashboardActivity extends PingDashboardActivityBase implements ServiceConnection {

    @Override
    protected void chooseBookmark() {

    }

    @Override
    protected void startPingService() {
        this.startService(new Intent(this, PingService.class));
        this.bindService(new Intent(this, PingService.class), this, 0);
    }

    @Override
    protected void stopPingService() {
        this.unbindService(this);
        this.stopService(new Intent(this, PingService.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_component_lesson);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d("temp_log", "connected");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d("temp_log", "disconnected");
    }

}
