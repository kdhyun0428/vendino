package com.example.front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.front.domain.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {
    EditText email1;
    EditText email2;
    EditText phone;
    EditText ninkname;
    EditText password1;
    EditText password2;
    CheckBox checkBox1;
    CheckBox checkBox2;
    Retrofit retrofit = new APIClient().getRetrofit();
    Service service = retrofit.create(Service.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email1 = (EditText) findViewById(R.id.email1);
        email2 = (EditText) findViewById(R.id.email2);
        phone = (EditText) findViewById(R.id.phone);
        ninkname = (EditText) findViewById(R.id.nickname);
        password1 = (EditText) findViewById(R.id.password1);
        password2 = (EditText) findViewById(R.id.password2);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
    }

    public void signUp(View view) {
        System.out.println("13123");
                if (
                email1.getText().length() == 0 ||//email 앞부분검사
                        email2.getText().length() == 0//email 뒷부분검사
        ) {
            Toast.makeText(getApplicationContext(), "이메일을 입력해주세요", Toast.LENGTH_LONG).show();
        } else if (
                ninkname.getText().length() == 0 //nickname 검사
        ) {
            Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요", Toast.LENGTH_LONG).show();
        } else if (
                !(password2.getText().toString().equals(password1.getText().toString()))//패스워드 2개 같은지 비교
        ) {
            Toast.makeText(getApplicationContext(), "패스워드가 같지 않습니다.", Toast.LENGTH_LONG).show();
        } else if (
                !checkBox1.isChecked() || //필수 체크 1
                        !checkBox2.isChecked() // 필수 체크 2
        ) {
            Toast.makeText(getApplicationContext(), "필수 체크를 해주세요", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("회원가입시작");
            User newUser = new User();
            newUser.setId(email1.getText().toString()+"@"+email2.getText().toString());
            newUser.setName(ninkname.getText().toString());
            newUser.setPw(password2.getText().toString());

            Call<JsonObject> call = service.saveUser(newUser);
            try {
                call.enqueue(
                        new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                                    Gson gson = new Gson();
                                    User _signUped = gson.fromJson(response.body(), User.class);
                                    intent.putExtra("user",_signUped);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);//액티비티 스택제거
                                    //참조 사이트 https://dduntorry.tistory.com/entry/Activity-%EC%8A%A4%ED%83%9D-%EC%A0%9C%EA%B1%B0
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                System.out.println(t.toString());
                                Toast.makeText(getApplicationContext(), "에러!!!", Toast.LENGTH_LONG).show();
                            }
                        }
                );

            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
            }
        }

    }
}