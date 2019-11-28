package com.itea.practice.fundamentals.start.menu;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itea.practice.fundamentals.R;

public final class MenuActivity extends AppCompatActivity {
    private ListAdapter adapter;
    private MenuListener menuListener;

    private ListView outputMenu;
    private View outputEmptyMsg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_menu);

        this.menuListener = new MenuListener();
        this.adapter = new MenuAdapter(this, menuListener);

        this.outputMenu = findViewById(R.id.output_list);
        this.outputEmptyMsg = findViewById(R.id.output_empty_msg);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.outputMenu.setAdapter(adapter);

        if (adapter.getCount() == 0) {
            outputEmptyMsg.setVisibility(View.VISIBLE);
        }
    }

}
