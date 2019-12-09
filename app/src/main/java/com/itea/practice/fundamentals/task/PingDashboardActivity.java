package com.itea.practice.fundamentals.task;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.itea.practice.components.PingLog;
import com.itea.practice.components.R;

public class PingDashboardActivity extends AppCompatActivity implements InternetReceiver.Listener{
    public final static String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private TextView outputTv;
    private TextView ouputDelay;
    private PingBinder pingBinder;
    private PingBinder.PingListener pingListener = new PingBinder.PingListener() {

        @Override
        public void onPing(PingLog log) {
            ouputDelay.setText(String.valueOf(log.getDuration()));
        }
    };
    private ImageView ivTumbler;
    InternetReceiver receiver;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            PingDashboardActivity.this.pingBinder = (PingBinder)binder;
            PingDashboardActivity.this.pingBinder.setOnPingListener(pingListener);
            changeIndicatorColor(R.color.accent);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        //dead service
            PingDashboardActivity.this.pingBinder = null;
           changeIndicatorColor(R.color.highlight_inactive);
        }
    };
    private void changeIndicatorColor(int colorId){
        ivTumbler.setColorFilter(
                ContextCompat.getColor(
                        PingDashboardActivity.this,colorId
                )
        );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_dashboard);
        outputTv = findViewById(R.id.output_connection);
        ouputDelay = findViewById(R.id.output_delay);
        ivTumbler = findViewById(R.id.btn_tumbler);
        ivTumbler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PingDashboardActivity.this, PingService.class);
                if(PingDashboardActivity.this.pingBinder == null) {
                    PingDashboardActivity.this.startService(intent);
                    PingDashboardActivity.this.bindService(intent, PingDashboardActivity.this.serviceConnection, 0);
                }else{
                    PingDashboardActivity.this.unbindService(PingDashboardActivity.this.serviceConnection);
                    PingDashboardActivity.this.pingBinder = null;
                    PingDashboardActivity.this.stopService(intent);
                    changeIndicatorColor(R.color.highlight_inactive);
                }
            }
        });
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
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(
                receiver
        );
    }

    @Override
    public void onInternetChanged(@Nullable String type) {
        outputTv.setText(
                type==null?getString(R.string.connection_none):type
        );
    }
}
