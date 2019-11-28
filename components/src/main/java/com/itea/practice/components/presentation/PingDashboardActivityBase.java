package com.itea.practice.components.presentation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itea.practice.components.R;

import java.util.Objects;

public abstract class PingDashboardActivityBase extends AppCompatActivity {

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.equals(btnRun)) PingDashboardActivityBase.this.startPingService();
            if (view.equals(btnStop)) PingDashboardActivityBase.this.stopPingService();
        }
    };

    private View btnRun;
    private View btnStop;

    protected abstract void startPingService();

    protected abstract void stopPingService();

    protected abstract void chooseBookmark();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.btnRun = this.findViewById(R.id.btn_run);
        this.btnRun.setOnClickListener(this.clickListener);

        this.btnStop = this.findViewById(R.id.btn_stop);
        this.btnStop.setOnClickListener(this.clickListener);
    }

}
