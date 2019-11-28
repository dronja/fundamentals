package com.itea.practice.fundamentals.components.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.itea.practice.components.presentation.PingDashboardActivityBase;
import com.itea.practice.components.R;
import com.itea.practice.fundamentals.components.service.PingService;

public class PingDashboardActivity extends PingDashboardActivityBase {

    @Override
    protected void chooseBookmark() {

    }

    @Override
    protected void startPingService() {
        this.startService(new Intent(this, PingService.class));
    }

    @Override
    protected void stopPingService() {
        this.stopService(new Intent(this, PingService.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_component_lesson);
    }

}
