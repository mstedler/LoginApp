package com.mstedler.loginapp;

import android.app.Application;

import com.mstedler.loginapp.events.RetrofitFailureEvent;
import com.mstedler.loginapp.util.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.paperdb.Paper;

public class LoginApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        Paper.init(this);
    }

    @Subscribe
    public void retrofitFailedEvent(RetrofitFailureEvent event){
        UIUtils.showShortMessage(getApplicationContext(), event.getMessage());
    }
}
