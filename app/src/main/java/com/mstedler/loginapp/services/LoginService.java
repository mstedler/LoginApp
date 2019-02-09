package com.mstedler.loginapp.services;

import com.google.gson.JsonObject;
import com.mstedler.loginapp.events.InvalidCredentialsEvent;
import com.mstedler.loginapp.events.RetrofitFailureEvent;
import com.mstedler.loginapp.events.UserLoggedInEvent;
import com.mstedler.loginapp.pojo.LoginData;
import com.mstedler.loginapp.pojo.User;
import com.mstedler.loginapp.util.Constant;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class LoginService {

    private LoginRetrofit loginRetrofit;

    public LoginService() {
        loginRetrofit = ServiceGenerator.createService(LoginRetrofit.class);
    }

    public void doLogin(LoginData loginData) {
        Call<User> userCall = loginRetrofit.login(loginData);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getToken().isEmpty()) {
                    EventBus.getDefault().post(new InvalidCredentialsEvent());
                } else {
                    EventBus.getDefault().post(new UserLoggedInEvent(response.body()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                EventBus.getDefault().post(new RetrofitFailureEvent(t.getMessage()));
            }
        });
    }

}

interface LoginRetrofit {
    @POST(Constant.LOGIN)
    Call<User> login(@Body LoginData loginData);
}