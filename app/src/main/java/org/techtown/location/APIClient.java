package org.techtown.location;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit;
    public Retrofit getRetrofit() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl( "http://192.168.10.120:8080/" );
        builder.addConverterFactory( GsonConverterFactory.create() );
        retrofit= builder.build();
        return retrofit;
    }
}
