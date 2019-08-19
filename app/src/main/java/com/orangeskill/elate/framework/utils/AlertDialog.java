package com.orangeskill.elate.framework.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.orangeskill.elate.R;

public class AlertDialog {

    private static AlertDialog dialog;

    private AlertDialog() {
    }

    public static AlertDialog getInstance() {
        if (dialog == null) {
            dialog = new AlertDialog();
        }
        return dialog;
    }


    public static void showDialog(Context context, String title, String message, String button1Text,
                                  DialogInterface.OnClickListener clickListener1, String button2Text,
                                  DialogInterface.OnClickListener clickListener2) {
        android.support.v7.app.AlertDialog alertDialog =
                new android.support.v7.app.AlertDialog.Builder(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert).setTitle(title)
                        .setMessage(message)
                        .setPositiveButton(button1Text, clickListener1)
                        .setNegativeButton(button2Text, clickListener2)
                        .create();
        alertDialog.show();
    }

    public ProgressDialog showProgressDialog(Context context, String msg){
        ProgressDialog progressdialog = new ProgressDialog(context);
        progressdialog.setMessage(msg);
        progressdialog.show();
        return progressdialog;
    }

    public static void showConfirmationDialog(Activity activity, DialogInterface.OnClickListener dialogClickListener, String message, String buttonPositive, String buttonNegative, boolean cancelable) {
        if(activity == null)
            return;
        if(TextUtils.isEmpty(message))
            buttonNegative = "Are you sure you want to cancel";
        if(TextUtils.isEmpty(buttonPositive))
            buttonNegative = "Ok";

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.
                setMessage(message).
                setPositiveButton(buttonPositive, dialogClickListener)
                .setNegativeButton(buttonNegative, dialogClickListener)
                .setCancelable(cancelable)
                .show();
    }

}
