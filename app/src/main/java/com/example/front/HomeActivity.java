package com.example.front;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.front.domain.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView = null;
    Retrofit retrofit = new APIClient().getRetrofit();
    Service service = retrofit.create(Service.class);
    User loginUser=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Bundle bundle = getIntent().getExtras();
        loginUser= (User) bundle.getSerializable("user");


        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        UserAdapter adapter = new UserAdapter();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //임시 데이터
        ArrayList<User> users = new ArrayList<User>();
        UserAdapter adapter = new UserAdapter();
        for(int i=0;i<5;i++){
            User user = new User();
            user.setName("user"+i);
            user.setPoint(i);
            adapter.addItem(user);

        }

                                        //users.add(user);
        recyclerView.setAdapter(adapter);

//        if(recyclerView!=null){
//            Call<JsonArray> call = service.getUseres();
////            try {
////                call.enqueue(
////                        new Callback<JsonArray>() {
////                            @Override
////                            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
////                                System.out.println("response : "+response.body());
////                                if(response.isSuccessful()){
////                                    JsonArray result =  response.body();
////                                    ArrayList<User> users = new ArrayList<User>();
////                                    UserAdapter adapter = new UserAdapter();
////
////                                    for(int i =0;i<result.size();i++){
////                                        Gson gson = new Gson();
////                                        User user = gson.fromJson(result.get(i), User.class);
////                                        System.out.println("user : "+result.get(i)+"/"+user.getName());
////                                        //users.add(user);
////                                        adapter.addItem(user);
////                                    }
////                                    recyclerView.setAdapter(adapter);
////
////                                }
////                            }
////
////                            @Override
////                            public void onFailure(Call<JsonArray> call, Throwable t) {
////
////                            }
////                        }
////                );
////
////            } catch (Exception e) {
////                System.out.println(e.toString());
////                e.printStackTrace();
////            }
//        }


    }

    public void goToSetting(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", loginUser);   // Object 넘기기
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void goToMap(View view) {
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", loginUser);   // Object 넘기기
        intent.putExtras(bundle);
        startActivity(intent);
    }
}