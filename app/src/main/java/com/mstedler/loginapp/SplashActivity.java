package com.mstedler.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mstedler.loginapp.util.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private final int splashTime = 500;
    private Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final SessionManager sessionManager = new SessionManager(this);
        splashThread = new Thread() {
            @Override

            public void run() {
                try {
                    int waited = 0;
                    while (waited < splashTime) {
                        sleep(100);
                        waited += 100;
                    }
                } catch (InterruptedException e) {
                    finish();
                } finally {
                    try {
                        Intent intent;
                        if (sessionManager.getLogged()) {
                            intent = new Intent(SplashActivity.this,
                                    MainActivity.class);
                        } else {
                            intent = new Intent(SplashActivity.this,
                                    LoginActivity.class);
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(SplashActivity.this, android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                        startActivity(intent, bundle);
                    } catch (Exception e2) {
                        Log.e("Exception %s", e2.getMessage());
                    }
                }
            }


        };
        splashThread.start();
    }


    @Override
    public void onBackPressed() {
        splashThread.interrupt();
        super.onBackPressed();

    }
}
