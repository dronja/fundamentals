package com.itea.practice.components.presentation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.itea.practice.components.R;

import java.util.Objects;

public abstract class PingDashboardActivityBase extends AppCompatActivity {

    private ImageView tumblerBtn;
    private View historyBtn;
    private TextView outputConnection;
    private TextView outputStatus;
    private TextView outputDelay;

    private void updateState(boolean isPingRunning) {

        Drawable icon = Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.ic_tumbler)).mutate();
        icon.setTint(
                ContextCompat.getColor(
                        this,
                        isPingRunning ? R.color.highlight_positive : R.color.highlight_negative)
        );

        this.tumblerBtn.setImageDrawable(icon);
        this.tumblerBtn.setOnClickListener(
                isPingRunning ? new View.OnClickListener() {
                    @Override
                    public void onClick(View ignored) {
                        PingDashboardActivityBase.this.stop();
                    }
                } : new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PingDashboardActivityBase.this.start();
                    }
                }
        );

        this.outputStatus.setText(
                getString(
                        isPingRunning
                                ? R.string.status_active
                                : R.string.status_inactive
                )
        );

        this.outputStatus.setTextColor(
                ContextCompat.getColor(
                        this,
                        isPingRunning
                                ? R.color.highlight_positive
                                : R.color.highlight_inactive
                )
        );
    }

    protected abstract void start();

    protected abstract void stop();

    protected abstract void navigateHistory();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        super.setContentView(R.layout.activity_ping_dashboard);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.tumblerBtn = findViewById(R.id.btn_tumbler);
        this.historyBtn = findViewById(R.id.btn_history);

        this.outputStatus = this.findViewById(R.id.output_status);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.updateState(false);
    }

}
