package com.itea.practice.fundamentals.components;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.itea.practice.components.presentation.PingDashboardActivityBase;
import com.itea.practice.components.R;

public class PingDashboardActivity extends PingDashboardActivityBase {

    @Override
    protected void chooseBookmark() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_component_lesson);
    }

}
