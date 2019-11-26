package com.itea.practice.components;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public abstract class ComponentsActivityBase extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_component_lesson);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title);
    }

}
