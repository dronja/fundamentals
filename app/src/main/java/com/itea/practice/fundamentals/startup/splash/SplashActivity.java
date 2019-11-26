package com.itea.practice.fundamentals.startup.splash;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.itea.practice.fundamentals.R;
import com.itea.practice.fundamentals.startup.menu.MenuStarter;

@SuppressWarnings("FieldCanBeLocal")
public final class SplashActivity extends AppCompatActivity {
    private final long DELAY = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new MenuStarter(this), DELAY);
    }

}
