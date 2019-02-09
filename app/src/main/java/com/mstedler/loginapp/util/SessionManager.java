package com.mstedler.loginapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import io.paperdb.Paper;

public class SessionManager {

    private static final String PREF = "LoginApp";
    private static final String LOGGED = "LOGGED";

    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;


    public SessionManager(Context context) {
        pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLogged(boolean logged) {
        editor.putBoolean(LOGGED, logged);
        editor.commit();
        Paper.book().delete("user");
    }

    public boolean getLogged() {
        return pref.getBoolean(LOGGED, false);
    }
}
