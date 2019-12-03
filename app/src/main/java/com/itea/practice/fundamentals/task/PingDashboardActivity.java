package com.itea.practice.fundamentals.task;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itea.practice.components.R;

public class PingDashboardActivity extends AppCompatActivity implements InternetReceiver.Listener{
    public final static String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private TextView outputTv;
    InternetReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_dashboard);
        outputTv = findViewById(R.id.output_connection);
        receiver = new InternetReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        this.registerReceiver(
                receiver, intentFilter
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.receiver.addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.receiver.removeListener(this);
    }

    @Override
    public void onInternetChanged(@Nullable String type) {
        outputTv.setText(
                type==null?getString(R.string.connection_none):type
        );
    }
}
