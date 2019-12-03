package com.itea.practice.fundamentals;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.itea.practice.components.InternetResolver;

import java.util.Objects;

public class PingDashboardActivity extends AppCompatActivity {

    private InternetResolver resolver;

    private ImageView tumblerBtn;
    private View historyBtn;
    private TextView outputConnection;
    private TextView outputStatus;
    private TextView outputDelay;

    private void updateState(boolean isActive) {
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

    private void start() {

    }

    private void stop() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(this.getSupportActionBar()).setTitle(com.itea.practice.components.R.string.title);
        this.setContentView(com.itea.practice.components.R.layout.activity_ping_dashboard);

        this.resolver = new InternetResolver(this);

        this.outputConnection = findViewById(com.itea.practice.components.R.id.output_connection);
        this.outputStatus = findViewById(com.itea.practice.components.R.id.output_status);
        this.outputDelay = findViewById(com.itea.practice.components.R.id.output_delay);

        this.tumblerBtn = findViewById(com.itea.practice.components.R.id.btn_tumbler);
        this.historyBtn = findViewById(com.itea.practice.components.R.id.btn_history);

        this.outputStatus = this.findViewById(com.itea.practice.components.R.id.output_status);
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

        updateState(true);
    }

}
