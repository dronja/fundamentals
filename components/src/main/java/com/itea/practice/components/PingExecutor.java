package com.itea.practice.components;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class PingExecutor {
    private volatile AtomicReference<String> address = new AtomicReference<>();
    private volatile AtomicReference<CallBack> callBack = new AtomicReference<>();

    //private final Action action = new Action();
    private Thread worker;

    private boolean isActive;

    public void execute(CallBack callBack, String address) {
        this.worker = new Thread(new Action());
        if (worker.isAlive() && !worker.isInterrupted()) return;

        this.callBack.set(callBack);
        this.address.set(address);

        this.worker.start();
        isActive = true;
    }
    public boolean isStarted(){
        return this.isActive;
    }

    public void interrupt() {
        if (!worker.isAlive() || worker.isInterrupted()) return;

        this.worker.interrupt();

        this.callBack.set(null);
        this.address.set(null);
        isActive = false;
    }

    private class Action implements Runnable {
        @Override
        public void run() {
            while (isActive) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                long started = System.currentTimeMillis();

                try {

                    if (address == null) continue;
                    Process process = Runtime.getRuntime().exec("/system/bin/ping -c 1 " + address);

                    int result = process.waitFor();
                    long finished = System.currentTimeMillis();

                    if (callBack.get() == null) continue;
                    if (result == 0) {
                        callBack.get().onSuccess(started, finished);
                    } else {
                        callBack.get().onFailure(started, finished);
                    }

                } catch (InterruptedException | IOException error) {
                    if (error instanceof InterruptedException) {
                        break;
                    } else {
                        error.printStackTrace();
                        callBack.get().onFailure(started, System.currentTimeMillis());
                    }
                }
            }
        }
    }

    public interface CallBack {
        void onSuccess(long started, long finished);

        void onFailure(long started, long finished);
    }

}
