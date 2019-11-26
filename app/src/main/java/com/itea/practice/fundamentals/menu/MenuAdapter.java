package com.itea.practice.fundamentals.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.itea.practice.fundamentals.R;

import java.util.Arrays;
import java.util.List;

final class MenuAdapter extends ArrayAdapter<String> {
    private static final int LAYOUT_ID = android.R.layout.simple_list_item_1;

    private final View.OnClickListener clickListener;
    private final List<String> data;

    MenuAdapter(@NonNull Context context, View.OnClickListener clickListener) {
        super(context, LAYOUT_ID);

        this.data = Arrays.asList(context.getResources().getStringArray(R.array.lessons));
        this.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = convertView == null
                ? LayoutInflater.from(getContext()).inflate(LAYOUT_ID, null)
                : convertView;

        TextView outputName = convertView.findViewById(android.R.id.text1);
        outputName.setText(data.get(position));

        convertView.setOnClickListener(clickListener);

        return convertView;
    }

}
