package com.itea.practice.components.presentation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itea.practice.components.R;

import java.util.Objects;

public abstract class PingDashboardActivityBase extends AppCompatActivity {

    protected abstract void chooseBookmark();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }
}
