/**
 * @useage Intent에서 testMode를 putExtra로 넘겨주어야함 VIDEO: 비디오 테스트, VOICE: 음성 테스트
 * */

package com.example.ourapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class TestingActivity extends AppCompatActivity {

    private TextView HeaderText;
    private TextView HeaderDesc;
    private TextView TestNumText;
    private Button getFileBtn;
    private TextView wranTxt1;
    private TextView wranTxt2;
    private TextView wranTxt3;
    private TextView wranTxt4;
    private TextView wranTxt5;
    private Button TestStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        //인텐트에서 Extra 가져오기
        Intent intent = getIntent();
        String testMode = intent.getStringExtra("testMode");

        //View Element들 가져오기
        HeaderText = (TextView)findViewById(R.id.TestHeaderTxt);
        HeaderDesc = (TextView)findViewById(R.id.TestDescTxt);

        //TODO: API에서 몇번째 테스트인지 받아와서 텍스트 바꿔주어야함 -> 서비스 API에서 가져와야함
        TestNumText = (TextView)findViewById(R.id.TestNumTxt);

        //TODO: 버튼을 누르면 파일을 가져오게 해야함
        getFileBtn = (Button)findViewById(R.id.GetFileBtn);

        wranTxt1 = (TextView)findViewById(R.id.warnTxt1);
        wranTxt2 = (TextView)findViewById(R.id.warnTxt2);
        wranTxt3 = (TextView)findViewById(R.id.warnTxt3);
        wranTxt4 = (TextView)findViewById(R.id.warnTxt4);
        wranTxt5 = (TextView)findViewById(R.id.warnTxt5);

        //TODO: 이 버튼을 누르면 가져온 파일을 ML API로 전송해 테스트 결과를 받아와서 테스트 결과 페이지로 넘어가야함
        TestStartBtn = (Button)findViewById(R.id.ConductTestBtn);

        String[] warnList;

        //영상 테스트일때
        if(testMode.equalsIgnoreCase("VIDEO")){
            warnList = getResources().getStringArray(R.array.video_warn_list);
            HeaderText.setText(getResources().getString(R.string.move_test_header));
            HeaderDesc.setText(getResources().getString(R.string.move_test_desc));
        }
        //음성 테스트일때
        else {
            warnList = getResources().getStringArray(R.array.voice_warn_list);
            HeaderText.setText(getResources().getString(R.string.voice_test_header));
            HeaderDesc.setText(getResources().getString(R.string.voice_test_desc));
        }

        //주의사항 텍스트 세팅
        wranTxt1.setText(warnList[0]);
        wranTxt2.setText(warnList[1]);
        wranTxt3.setText(warnList[2]);
        wranTxt4.setText(warnList[3]);
        wranTxt5.setText(warnList[4]);
    }
}