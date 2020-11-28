package com.example.calculator_project;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

public class DialogInfo {
    public DialogInfo(Context context) {
        final Dialog dialog=new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_layout);
        dialog.show();
    }
}
