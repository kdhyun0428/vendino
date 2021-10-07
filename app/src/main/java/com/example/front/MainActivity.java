package com.example.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit = new APIClient().getRetrofit();
    Service service = retrofit.create(Service.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        try {
            Call<JsonArray> call = service.getUseres();

            call.enqueue(
                    new Callback<JsonArray>() {
                        @Override
                        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                            Log.d("성공","성공");
                            if(response.isSuccessful()){
                                Log.d("데이터",response.body().toString());

                            }

                        }

                        @Override
                        public void onFailure(Call<JsonArray> call, Throwable t) {
                            Log.d("실패","실패"+t.toString());

                        }
                    }
            );
        } catch (Exception e) {
            Log.d("sssssss", e.toString());
        }


    }

    public void btn(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_main.class);
        startActivity(intent);
    }
}