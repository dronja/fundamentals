package com.itea.practice.components.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.itea.practice.components.model.PingLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class PingStatsStorage {
    private final String PREFS_FILE_NAME = "ping_stats_prefs";
    private final String PREFS_LOGS_KEY = "ping_logs";

    private Gson gson;
    private SharedPreferences preferences;
    private List<PingLog> logs;

    public PingStatsStorage(Context context) {
        this.gson = new Gson();
        this.preferences = context.getSharedPreferences(this.PREFS_FILE_NAME, Context.MODE_PRIVATE);
        this.logs = this.parseLogs(this.preferences.getString(this.PREFS_LOGS_KEY, null));
    }

    private List<PingLog> parseLogs(String json) {
        try {
            return Arrays.asList(this.gson.fromJson(json, PingLog[].class));
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }

    public void insertLog(PingLog log) {
        this.logs.add(log);
        this.preferences.edit().putString(this.PREFS_LOGS_KEY, this.gson.toJson(this.logs)).apply();
    }

    public PingLog getLog(int index) {
        return this.logs.get(index);
    }

    public List<PingLog> getLogs() {
        return this.logs;
    }

}
