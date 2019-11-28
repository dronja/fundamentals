package com.itea.practice.components.service;

import android.app.Service;

import com.itea.practice.components.data.PingExecutor;
import com.itea.practice.components.model.PingLog;

public abstract class PingServiceBase extends Service {

    private final PingExecutor executor = new PingExecutor();
    private final PingCallback callback = new PingCallback();

    protected abstract void onResult(PingLog result);

    public void interrupt() {
        this.executor.interrupt();
    }

    public void run() {
        this.executor.execute(this.callback, "8.8.8.8");
    }

    private final class PingCallback implements PingExecutor.CallBack {
        @Override
        public void onSuccess(long started, long finished) {
            onResult(new PingLog(true, (finished - started), started));
        }

        @Override
        public void onFailure(long started, long finished) {
            onResult(new PingLog(false, (finished - started), started));
        }
    }

}
