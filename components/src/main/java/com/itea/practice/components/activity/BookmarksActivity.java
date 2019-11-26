package com.itea.practice.components.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BookmarksActivity extends AppCompatActivity {
    private static final Uri URI = Uri.parse("content://com.android.chrome.browser/history");
    private static final String[] PROJECTION = {"title", "url"};
    private static final String SELECTION = "bookmark = 0";

    private ListView list;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        list = (ListView) getWindow().getDecorView().getRootView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Cursor cursor = getContentResolver().query(
                URI,
                PROJECTION,
                SELECTION,
                null,
                null
        );

        if (cursor != null) {
            cursor.close();
        }

    }

}
