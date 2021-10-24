package com.example.front;

import com.example.front.domain.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Service {
    @GET("users")
    Call<JsonArray> getUseres();

    @POST("user-save")
    Call<JsonObject> saveUser(@Body User user);

    @POST("login")
    Call<JsonObject> login(@Body User user);
}
