package com.itea.practice.components.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.itea.practice.components.R;

import java.util.Objects;

public abstract class PingDashboardActivityBase extends AppCompatActivity {

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.equals(btnStats)) PingDashboardActivityBase.this.showStats();
            if (view.equals(btnRun)) PingDashboardActivityBase.this.startPingService();
            if (view.equals(btnStop)) PingDashboardActivityBase.this.stopPingService();
        }
    };

    private View btnStats;
    private View btnRun;
    private View btnStop;
    private TextView outputStatus;

    private void update(boolean isPingRunning) {
        this.btnRun.setEnabled(!isPingRunning);
        this.btnStop.setEnabled(isPingRunning);

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
                                : R.color.highlight_negative
                )
        );
    }

    protected abstract void showStats();

    protected abstract void startPingService();

    @CallSuper
    protected void stopPingService() {
        this.notifyPingStopped();
    }

    protected void notifyPingRunning() {
        this.update(true);
    }

    protected void notifyPingStopped() {
        this.update(false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.btnStats = this.findViewById(R.id.btn_stats);
        this.btnStats.setOnClickListener(this.clickListener);

        this.btnRun = this.findViewById(R.id.btn_run);
        this.btnRun.setOnClickListener(this.clickListener);

        this.btnStop = this.findViewById(R.id.btn_stop);
        this.btnStop.setOnClickListener(this.clickListener);

        this.outputStatus = this.findViewById(R.id.output_status);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.update(false);
    }

}
