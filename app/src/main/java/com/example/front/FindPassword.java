package com.example.front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Retrofit;

public class FindPassword extends AppCompatActivity {

    Retrofit retrofit = new APIClient().getRetrofit();
    Service service = retrofit.create(Service.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

    }


}