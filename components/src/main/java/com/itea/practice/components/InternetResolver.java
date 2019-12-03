package com.itea.practice.components;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.Nullable;

import java.util.Objects;

public class InternetResolver {
    public final static String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private ConnectivityManager manager;

    public InternetResolver(Context context) {
        this.manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Nullable
    @SuppressWarnings("deprecation")
    public String determineConnection() {
        try {
            final NetworkInfo ni = Objects.requireNonNull(manager).getActiveNetworkInfo();
            return ni != null && ni.isConnectedOrConnecting() ? ni.getTypeName() : null;
        } catch (NullPointerException ignored) {
            return null;
        }
    }

}
