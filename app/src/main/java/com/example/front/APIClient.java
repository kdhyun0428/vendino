package com.example.front;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retorfit;
    public Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder() ;
        builder.baseUrl("http://203.245.44.73:8080/");

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        builder.client(okHttpClient);
//        builder.baseUrl("http://192.168.1.101:8080/");
        builder.addConverterFactory( GsonConverterFactory.create());
        retorfit = builder.build();

        return retorfit;
    }
}
