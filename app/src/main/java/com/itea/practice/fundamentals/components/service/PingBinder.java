package com.itea.practice.fundamentals.components.service;

import android.os.Binder;

public class PingBinder extends Binder {
    private PingService pingService;

    PingBinder(PingService pingService) {
        this.pingService = pingService;
    }

    public void interrupt() {
        this.pingService.interrupt();
    }

    public void run() {
        this.pingService.run();
    }

}
