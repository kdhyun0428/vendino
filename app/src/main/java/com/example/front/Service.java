package com.example.front;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("users")
    Call<JsonArray> getUseres();
}
