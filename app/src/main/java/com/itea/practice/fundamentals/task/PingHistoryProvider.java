package com.itea.practice.fundamentals.task;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.itea.practice.components.PingLog;
import com.itea.practice.components.PingStatsStorage;

import java.security.InvalidParameterException;

public class PingHistoryProvider extends ContentProvider {

    public static  final String AUTH = "com.itea.practice.fundamentals.ping";
    public static final String SCHEME = "content://";
    private PingStatsStorage storage;

    public static ContentValues logToValue(PingLog log){
        ContentValues contentValues = new ContentValues();
        contentValues.put();
        return contentValues;

    }
    public static PingLog valueToLog(ContentValues values){

    }

    @Override
    public boolean onCreate() {
        Log.d("myLod", "create");
        if(getContext()==null)
            return false;

        storage = new PingStatsStorage(getContext());
        return true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d("myLod", "query");

        return new AbstractCursor() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public String[] getColumnNames() {
                return new String[0];
            }

            @Override
            public String getString(int column) {
                return null;
            }

            @Override
            public short getShort(int column) {
                return 0;
            }

            @Override
            public int getInt(int column) {
                if(column == 0){
                    return storage.getLogs().get(this.getPosition());
                }
                return 0;

            }

            @Override
            public long getLong(int column) {
                PingLog row = storage.getLog(column);
                switch (column){
                    case 1:
                        return row.getDuration();
                    case 2:
                        return row.getDate();

                }
                throw new InvalidParameterException();
            }

            @Override
            public float getFloat(int column) {
                return 0;
            }

            @Override
            public double getDouble(int column) {
                return 0;
            }

            @Override
            public boolean isNull(int column) {
                return false;
            }
        };
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
