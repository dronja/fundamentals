package com.itea.practice.fundamentals.start.menu;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.itea.practice.fundamentals.Tasks.Components.PingDashBoardActivity;

public final class MenuListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Context context = view.getContext();

        switch (((ViewGroup) view.getParent()).indexOfChild(view)) {
            case 0:
                Intent dashBoardIntent = new Intent(context, PingDashBoardActivity.class);
                context.startActivity(dashBoardIntent );
                break;
            default:
                break;
        }
    }

}
