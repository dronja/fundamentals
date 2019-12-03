package com.itea.practice.fundamentals;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.itea.practice.components.presentation.PingDashboardActivityBase;

import java.util.Objects;

public class PingDashboardActivity extends PingDashboardActivityBase {

    @Override
    protected void updateState(boolean isActive) {
        Drawable icon = Objects.requireNonNull(ContextCompat.getDrawable(this, com.itea.practice.components.R.drawable.ic_tumbler)).mutate();
        icon.setTint(
                ContextCompat.getColor(
                        this,
                        isActive ? com.itea.practice.components.R.color.highlight_positive : com.itea.practice.components.R.color.highlight_negative)
        );

        this.tumblerBtn.setImageDrawable(icon);
        this.tumblerBtn.setOnClickListener(
                isActive ? new View.OnClickListener() {
                    @Override
                    public void onClick(View ignored) {
                        PingDashboardActivity.this.stop();
                    }
                } : new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PingDashboardActivity.this.start();
                    }
                }
        );

        this.outputStatus.setText(getString(isActive ? com.itea.practice.components.R.string.status_active : com.itea.practice.components.R.string.status_inactive));
        this.outputStatus.setTextColor(
                ContextCompat.getColor(
                        this,
                        isActive ? com.itea.practice.components.R.color.highlight_positive : com.itea.practice.components.R.color.highlight_inactive
                )
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        this.registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String type = resolver.determineConnection();
                        outputConnection.setText(type == null ? getString(com.itea.practice.components.R.string.connection_none) : type);
                    }
                },
                filter
        );
    }

    @Override
    protected void start() {

    }

    @Override
    protected void stop() {

    }

    @Override
    protected void navigateHistory() {

    }

}
