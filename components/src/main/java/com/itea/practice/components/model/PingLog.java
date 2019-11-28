package com.itea.practice.components.model;

import java.util.Calendar;

public class PingLog {
    private boolean result;
    private long duration;
    private Calendar date;

    public PingLog(boolean result, long duration, Calendar date) {
        this.result = result;
        this.duration = duration;
        this.date = date;
    }

    public boolean isResult() {
        return result;
    }

    public long getDuration() {
        return duration;
    }

    public Calendar getDate() {
        return date;
    }

}
