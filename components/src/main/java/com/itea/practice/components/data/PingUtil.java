package com.itea.practice.components.data;

import java.io.IOException;

public class PingUtil {

    public void execute(CallBack callBack, String address) {
        long started = System.currentTimeMillis();

        try {

            Process process = Runtime.getRuntime().exec("/system/bin/ping -c 1 " + address);
            int result = process.waitFor();

            long finished = System.currentTimeMillis();
            if (result == 0) {
                callBack.onSuccess(started, finished);
            } else {
                callBack.onFailure(started, finished);
            }

        } catch (InterruptedException | IOException error) {
            error.printStackTrace();
            callBack.onFailure(started, System.currentTimeMillis());
        }
    }

    public abstract class CallBack {
        public abstract void onSuccess(long started, long finished);

        public abstract void onFailure(long started, long finished);
    }

}
