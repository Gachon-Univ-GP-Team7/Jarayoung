package com.example.ourapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터 베이스 서버에 연동시킬 수 있는 객체
    private EditText edtEmail, edtPassword, edtCheckPwd; //회원가입 입력 필드
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //초기화
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("OurApplication");

        edtEmail = findViewById(R.id.regiEmail);
        edtPassword = findViewById(R.id.regiPass);
        btnRegister = findViewById(R.id.btnRegister);

        //비밀번호 확인은 여기서 바로 수행.
        //다르면 토스트 메시지 출력(나중에는 색이 변하거나 체크가 생겼으면 좋겠음)-> 회원가입 누를때 ?
        edtCheckPwd = findViewById(R.id.regiPassCheck);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 처리 시작
                String strEmail = edtEmail.getText().toString(); //사용자가 이메일을 입력한것을 가져와서 문자열로 변홚시켜 저장.
                String strPwd = edtPassword.getText().toString();
                String strCheckPwd = edtCheckPwd.getText().toString();

                if (!strPwd.equals(strCheckPwd)) {
                    Toast.makeText(RegisterActivity.this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    //Firebase Auth진행 //activity에는 현재 액티비티 써줌
                    mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        //회원가입이 이뤄졌을 때의 처리가 들어감.
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //성공
                            if (task.isSuccessful()) {//task이름으로 결과값줌.
                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();//현재 회원가입이 된 유저를 갖고 와서 firebaseUser에 넣음.
                                UserAccount account = new UserAccount();
                                account.setIdToken(firebaseUser.getUid());
                                account.setEmailId(firebaseUser.getEmail());
                                account.setPassword(strPwd);

                                //setValue : database에 insert(삽입)하는 행위/ uid를 키값으로 하고 저 값들을 넣어줌.
                                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                                Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}