package com.itea.practice.fundamentals.start.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public final class MenuListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Context context = view.getContext();

        switch (((ViewGroup) view.getParent()).indexOfChild(view)) {
            case 0:

                break;
        }
    }

}
