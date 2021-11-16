package com.example.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.front.domain.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FindId extends AppCompatActivity {
    EditText phone;
    TextView findId;
    Retrofit retrofit = new APIClient().getRetrofit();
    Service service = retrofit.create(Service.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);
        phone = findViewById(R.id.idphone);
        findId = findViewById(R.id.findId);
    }

    public void searchId(View view) {
        System.out.println(phone.getText().toString());
        Call<JsonObject> call = service.findId(phone.getText().toString());
        try {
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()){
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        Gson gson = new Gson();
                        User _logined = gson.fromJson(response.body(), User.class);
                        findId.setText(_logined.getId());
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();

                }
            });

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

    }
}