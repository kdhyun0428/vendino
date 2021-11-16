package com.example.front;

import com.example.front.domain.ChangePwForm;
import com.example.front.domain.ReportParam;
import com.example.front.domain.User;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Service {
    @GET("users")
    Call<JsonArray> getUseres();

    @POST("user-save")
    Call<JsonObject> saveUser(@Body User user);

    @POST("login")
    Call<JsonObject> login(@Body User user);

    @POST("report-save")
    Call<JsonObject> saveReport(@Body ReportParam reportParam);

    @POST("changePw")
    Call<JsonObject> changePw(@Body ChangePwForm cpf);

    @GET("findId")
    Call<JsonObject> findId(@Query("phone") String phone);
}
