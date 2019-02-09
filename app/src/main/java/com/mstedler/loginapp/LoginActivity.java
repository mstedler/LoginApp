package com.mstedler.loginapp;

import android.content.Intent;

import com.google.android.material.textfield.TextInputLayout;
import com.mstedler.loginapp.events.InvalidCredentialsEvent;
import com.mstedler.loginapp.events.RetrofitFailureEvent;
import com.mstedler.loginapp.events.UserLoggedInEvent;
import com.mstedler.loginapp.pojo.LoginData;
import com.mstedler.loginapp.services.LoginService;
import com.mstedler.loginapp.util.NetworkUtils;
import com.mstedler.loginapp.util.SessionManager;
import com.mstedler.loginapp.util.StringUtils;
import com.mstedler.loginapp.util.UIUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;

    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;

    LoginService loginService;

    private SessionManager sessionManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginService = new LoginService();
        sessionManager = new SessionManager(this);
    }

    @OnClick(R.id.btnLogin)
    public void btnLoginClick(View view) {

        if(!NetworkUtils.isOnline()) {
            UIUtils.showShortMessage(this, "Sem conexão com a internet.");
        }
        LoginData loginData = new LoginData();
        String email = StringUtils.getValueOf(tilEmail.getEditText());
        String pass = StringUtils.getValueOf(tilPassword.getEditText());
        tilEmail.setError(null);
        tilPassword.setError(null);

        if(!StringUtils.isValidEmail(email)) {
            tilEmail.setError("Email inválido");
            return;
        }

        if(pass.length() <= 4) {
            tilPassword.setError("Senha muito curta");
            return;
        }

        loginData.setEmail(email);
        loginData.setSenha(pass);

        UIUtils.showProcess(this, "Logando...");
        loginService.doLogin(loginData);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onUserLoggedInEvent(UserLoggedInEvent userLoggedInEvent) {
        UIUtils.closeProcess();
        sessionManager.setLogged(true);
        Paper.book().write("user", userLoggedInEvent.getUser());
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Subscribe
    public void onInvalidCredentialsEvent(InvalidCredentialsEvent invalidCredentialsEvent){
        UIUtils.closeProcess();
        UIUtils.showShortMessage(this, "Login ou Senha inválidos");
    }

    @Subscribe
    public void retrofitFailedEvent(RetrofitFailureEvent event){
        UIUtils.closeProcess();
    }
}

