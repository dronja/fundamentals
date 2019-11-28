package com.itea.practice.fundamentals.start.menu;

import android.content.Context;
import android.content.Intent;

public final class MenuStarter implements Runnable {
    private Context context;

    public MenuStarter(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        this.context.startActivity(new Intent(this.context, MenuActivity.class));
    }

}
