package com.example.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.front.domain.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit = new APIClient().getRetrofit();
    Service service = retrofit.create(Service.class);
    EditText id = null;
    EditText pw = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.email);
        pw = findViewById(R.id.pw);


    }

//    public void btn(View view) {
//        Intent intent = new Intent(getApplicationContext(), FindePassword.class);
//        startActivity(intent);
//    }

    public void findPassword(View view) {
        Intent intent = new Intent(getApplicationContext(), FindPassword.class);
        startActivity(intent);
    }

    public void loginHandler(View view) {
        User loginUser = new User();

        loginUser.setId(id.getText().toString());
        loginUser.setPw(pw.getText().toString());

        Call<JsonObject> call = service.login(loginUser);
        try {
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    System.out.println(response.body().toString());
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    Gson gson = new Gson();
                    User _logined = gson.fromJson(response.body(), User.class);
                    intent.putExtra("user",_logined);
                    //        User person = (Person)intent.getSerializableExtra("user");
                    startActivity(intent);
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

    public void signUpHandler(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }
}