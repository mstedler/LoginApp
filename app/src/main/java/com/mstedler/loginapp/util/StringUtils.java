package com.mstedler.loginapp.util;

import android.text.TextUtils;
import android.widget.EditText;

public class StringUtils {

    private StringUtils() {

    }

    public static String getValueOf(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
