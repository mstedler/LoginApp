package com.mstedler.loginapp.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class UIUtils {
    private static ProgressDialog progressDialog;

    private UIUtils() {

    }
    public static void showShortMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showAlertBuilder(Activity activity, @Nullable View view, CharSequence titleMessage,
                                        CharSequence message, CharSequence positiveButtonMessage, CharSequence cancelButtonMessage, DialogInterface.OnClickListener clickListener, DialogInterface.OnCancelListener cancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (!(titleMessage.length() == 0))
            builder.setTitle(titleMessage);
        if (!(message.length() == 0))
            builder.setMessage(message);
        if (view != null)
            builder.setView(view);

        builder.setPositiveButton(positiveButtonMessage, clickListener);
        builder.setNegativeButton(cancelButtonMessage, clickListener);
        builder.setOnCancelListener(cancelListener);
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void showProcess(Context context, String message) {
        closeProcess();
        progressDialog = new ProgressDialog(context);
        if (!message.isEmpty())
            progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    public static void closeProcess() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
