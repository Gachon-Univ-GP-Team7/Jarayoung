package com.example.ourapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ourapplication.Data.auth.PostLoginReq;
import com.example.ourapplication.Data.auth.PostLoginRes;
import com.example.ourapplication.Service.AuthService;
import com.example.ourapplication.Utils.SharedPreferenceManager;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private final AuthService authService = new AuthService();
    private final SharedPreferenceManager shared = new SharedPreferenceManager();
    private EditText edtEmail, edtPassword; //로그인 입력 필드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPW);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 요청
                String strEmail = edtEmail.getText().toString();
                String strPwd = edtPassword.getText().toString();

                Log.d("Login", strEmail);
                Log.d("Login", strPwd);

                //TODO: Login API 바인딩
                PostLoginReq postLoginReq = new PostLoginReq(strEmail, strPwd);
                final PostLoginRes[] postLoginRes = new PostLoginRes[1];

                new Thread(() -> {
                    try {
                        postLoginRes[0] = authService.callLoginApi(postLoginReq);

                        Log.d("Login", Integer.toString(postLoginRes[0].getUserIdx()));
                        shared.setUserIdx(postLoginRes[0].getUserIdx());

                        if(!postLoginRes[0].isLoginSuccess()) {
                            Toast onLoginFail = Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG);
                            onLoginFail.show();
                        }
                        else{
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("shared", shared);
                            startActivity(intent);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        });

        Button btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}