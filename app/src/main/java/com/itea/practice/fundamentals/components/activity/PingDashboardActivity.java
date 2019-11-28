package com.itea.practice.fundamentals.components.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.itea.practice.components.presentation.BookmarksActivity;
import com.itea.practice.components.presentation.PingDashboardActivityBase;
import com.itea.practice.components.R;
import com.itea.practice.fundamentals.components.service.PingBinder;
import com.itea.practice.fundamentals.components.service.PingService;

public class PingDashboardActivity extends PingDashboardActivityBase implements ServiceConnection {
    private PingBinder binder;

    private void bind() {
        this.bindService(new Intent(this, PingService.class), this, 0);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        this.bind();
    }

    @Override
    protected void showStats() {
        this.startActivity(new Intent(this, BookmarksActivity.class));
    }

    @Override
    protected void startPingService() {
        this.startService(new Intent(this, PingService.class));
        this.bind();
    }

    @Override
    protected void stopPingService() {
        super.stopPingService();

        this.binder.interrupt();
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
        this.binder = (PingBinder) service;
        this.binder.run();

        super.notifyPingRunning();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        super.notifyPingStopped();
    }

}
