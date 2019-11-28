package com.itea.practice.components.model;

public class PingLog {
    private boolean result;
    private long duration;
    private long date;

    public PingLog(boolean result, long duration, long date) {
        this.result = result;
        this.duration = duration;
        this.date = date;
    }

    public boolean isSuccessful() {
        return result;
    }

    public long getDuration() {
        return duration;
    }

    public long getDate() {
        return date;
    }

}
