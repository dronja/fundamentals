package com.itea.practice.fundamentals.task;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PingHistoryProvider extends ContentProvider {
    public static  final String AUTH = "com.itea.practice.fundamentals.ping";
    public static final String SCHEME = "content://";
    @Override
    public boolean onCreate() {
        Log.d("myLod", "create");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d("myLod", "query");
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d("myLod", "getType");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d("myLod", "insert");
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d("myLod", "delete");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d("myLod", "update");
        return 0;
    }
}
