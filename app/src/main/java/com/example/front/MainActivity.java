package com.example.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit = new APIClient().getRetrofit();
    Service service = retrofit.create(Service.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent intent = new Intent(getApplicationContext(), HomeActivity
                .class);
        startActivity(intent);
    }

    public void signUpHandler(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }
}