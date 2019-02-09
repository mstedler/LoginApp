package com.mstedler.loginapp.services;

import com.google.gson.GsonBuilder;
import com.mstedler.loginapp.util.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mateus on 08/02/2019
 */
public class ServiceGenerator {

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.
            Builder().
            connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES);

    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Constant.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()));

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}