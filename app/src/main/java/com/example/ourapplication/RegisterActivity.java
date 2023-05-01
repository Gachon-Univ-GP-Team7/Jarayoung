package com.example.ourapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication.Data.auth.PostApplyReq;
import com.example.ourapplication.Data.auth.PostApplyRes;
import com.example.ourapplication.Service.AuthService;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    private final AuthService authService = new AuthService();
    private EditText edtEmail, edtPassword, edtCheckPwd, edtId, edtBabyName, edtBabyBorn; //회원가입 입력 필드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //초기화
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("OurApplication");

        edtId = findViewById(R.id.legiName);
        edtEmail = findViewById(R.id.regiEmail);
        edtPassword = findViewById(R.id.regiPass);
        Button btnRegister = findViewById(R.id.btnRegister);
        edtBabyName = findViewById(R.id.babyName);
        edtBabyBorn = findViewById(R.id.babyBorn);

        //비밀번호 확인은 여기서 바로 수행.
        //다르면 토스트 메시지 출력(나중에는 색이 변하거나 체크가 생겼으면 좋겠음)-> 회원가입 누를때 ?
        edtCheckPwd = findViewById(R.id.regiPassCheck);

        btnRegister.setOnClickListener(v -> {
            //회원가입 처리 시작
            String strEmail = edtEmail.getText().toString(); //사용자가 이메일을 입력한것을 가져와서 문자열로 변환시켜 저장.
            String strPwd = edtPassword.getText().toString();
            String strCheckPwd = edtCheckPwd.getText().toString();
            String strName = edtId.getText().toString();
            String strBabyName = edtBabyName.getText().toString();
            String strBabyBorn = edtBabyBorn.getText().toString();

            //비밀번호 확인
            if (!strPwd.equals(strCheckPwd)) {
                Toast.makeText(RegisterActivity.this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
            } else {
                //TODO: 회원가입 API 바인딩
                PostApplyReq postApplyReq = new PostApplyReq(strName, strBabyName, strBabyBorn, strPwd, strEmail);

                new Thread(() -> {
                    try {
                        PostApplyRes postApplyRes = authService.callApplyApi(postApplyReq);
                        Log.d("Apply", "Apply : " + postApplyRes.isApplySuccess());

                        if(postApplyRes.isApplySuccess()){
                            Toast toast = Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG);
                            toast.show();

                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast toast = Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        });
    }
}