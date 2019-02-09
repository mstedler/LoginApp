package com.mstedler.loginapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.mstedler.loginapp.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class UIUtils {

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
}
