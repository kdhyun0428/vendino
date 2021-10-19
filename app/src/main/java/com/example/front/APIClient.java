package com.example.front;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retorfit;
    public Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder() ;
        builder.baseUrl("http://192.168.219.103:8080/");
        builder.addConverterFactory( GsonConverterFactory.create());
        retorfit = builder.build();

        return retorfit;
    }
}
