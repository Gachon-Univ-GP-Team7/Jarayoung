package com.example.ourapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView typeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //인텐트에서 Extra 가져오기
        Intent intent = getIntent();
        String testMode = intent.getStringExtra("testMode");
        String date = intent.getStringExtra("testDate");

        typeTxt = (TextView)findViewById(R.id.typeTxt);

        if(testMode.equalsIgnoreCase("VIDEO")){
            typeTxt.setText("검사분야 : 행동발달\\n검사일자 : " + date);
        }
        else{
            typeTxt.setText("검사분야 : 언어발달\\n검사일자 : " + date);
        }
    }
}