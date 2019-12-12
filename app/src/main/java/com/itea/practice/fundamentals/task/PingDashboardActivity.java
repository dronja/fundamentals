package com.itea.practice.fundamentals.task;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.itea.practice.components.PingLog;
import com.itea.practice.components.R;

import java.util.List;

public class PingDashboardActivity extends AppCompatActivity implements InternetReceiver.Listener{
    public final static String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private final String KEY_STATE = "state.connection";
    private final String KEY_DELAY = "state.delay";
    private final String KEY_STATUS = "state.status";
    private TextView outputTv;
    private TextView ouputDelay;
    private TextView outputStatus;
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
        outputStatus = findViewById(R.id.output_status);
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
       /* receiver = new InternetReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        this.registerReceiver(
                receiver, intentFilter
        );*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        receiver = new InternetReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        this.registerReceiver(
                receiver, intentFilter
        );

        this.receiver.addListener(this);

        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

        assert manager != null;
        List<ActivityManager.RunningServiceInfo> listRunning = manager.getRunningServices(Integer.MAX_VALUE);

        for(ActivityManager.RunningServiceInfo info : listRunning){
            if(info.service.getClassName().equalsIgnoreCase(PingService.class.getName())){
                bindService(new Intent(this, PingService.class), serviceConnection, 0);
                break;
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        this.receiver.removeListener(this);
        this.unregisterReceiver(receiver);
        this.unbindService(serviceConnection);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle state) {
        super.onSaveInstanceState(state);
        state.putString(KEY_STATE, outputTv.getText().toString());
        state.putString(KEY_DELAY, ouputDelay.getText().toString());
        state.putString(KEY_STATUS, outputStatus.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle state) {
        super.onRestoreInstanceState(state);
        String sDefault = getString(R.string.connection_none);

            outputTv.setText(state.getString(KEY_STATE, sDefault));
            outputStatus.setText(state.getString(KEY_STATUS, sDefault));
            ouputDelay.setText(state.getString(KEY_DELAY, "0"));

    }

    @Override
    protected void onPostCreate(@Nullable Bundle state) {
        super.onPostCreate(state);

       /* String sDefault = getString(R.string.connection_none);
        if(state!= null){
            outputTv.setText(state.getString(KEY_STATE, sDefault));
            outputStatus.setText(state.getString(KEY_STATUS, sDefault));
            ouputDelay.setText(state.getString(KEY_DELAY, "0"));
        }*/


    }

    @Override
    public void onInternetChanged(@Nullable String type) {
        outputTv.setText(
                type==null?getString(R.string.connection_none):type
        );
    }
}
