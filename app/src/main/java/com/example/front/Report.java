package com.example.front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.front.domain.ReportParam;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Report extends AppCompatActivity {
    EditText content=null;
    Retrofit retrofit = new APIClient().getRetrofit();
    Service service = retrofit.create(Service.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        content = findViewById(R.id.report);
    }


    public void send(View view) {
        if(content.getText().toString().length()==0){
            Toast.makeText(getApplicationContext(),"문의사항을 입력하세요",Toast.LENGTH_LONG).show();
            return;
        }
        finish();
//        ReportParam param = new ReportParam();
//        param.setContent(content.getText().toString());
//        Call<JsonObject> call = service.saveReport(param);
//        call.enqueue(
//                new Callback<JsonObject>() {
//                    @Override
//                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<JsonObject> call, Throwable t) {
//
//                    }
//                }
//        );
    }
}