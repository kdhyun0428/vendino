package com.example.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.front.domain.User;

public class SettingActivity extends AppCompatActivity {
    User loginUser=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Bundle bundle2 = getIntent().getExtras();
        loginUser= (User) bundle2.getSerializable("user");
    }

    public void btn(View view) {
        Intent intent = new Intent(getApplicationContext(), Report.class);


        Bundle bundle = new Bundle();
        bundle.putSerializable("user", loginUser);   // Object 넘기기
        intent.putExtras(bundle);
        startActivity(intent);
    }
}