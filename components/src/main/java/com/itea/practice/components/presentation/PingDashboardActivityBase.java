package com.itea.practice.components.presentation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itea.practice.components.R;
import com.itea.practice.components.data.InternetResolver;

import java.util.Objects;

public abstract class PingDashboardActivityBase extends AppCompatActivity {

    protected InternetResolver resolver;

    protected ImageView tumblerBtn;
    protected View historyBtn;
    protected TextView outputConnection;
    protected TextView outputStatus;
    protected TextView outputDelay;

    protected abstract void start();

    protected abstract void stop();

    protected abstract void updateState(boolean isActive);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(this.getSupportActionBar()).setTitle(R.string.title);
        this.setContentView(R.layout.activity_ping_dashboard);

        this.resolver = new InternetResolver(this);

        this.outputConnection = findViewById(R.id.output_connection);
        this.outputStatus = findViewById(R.id.output_status);
        this.outputDelay = findViewById(R.id.output_delay);

        this.tumblerBtn = findViewById(R.id.btn_tumbler);
        this.historyBtn = findViewById(R.id.btn_history);

        this.outputStatus = this.findViewById(R.id.output_status);
    }

}
