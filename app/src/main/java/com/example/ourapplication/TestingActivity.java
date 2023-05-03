/**
 * @useage Intent에서 testMode를 putExtra로 넘겨주어야함 VIDEO: 비디오 테스트, VOICE: 음성 테스트
 * */

package com.example.ourapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ourapplication.Data.Test.GetTestViewRes;
import com.example.ourapplication.Data.Test.PostTestRes;
import com.example.ourapplication.Service.TestService;
import com.example.ourapplication.Utils.SharedPreferenceManager;

import java.io.IOException;

public class TestingActivity extends AppCompatActivity {

    TestService testService = new TestService();
    private GetTestViewRes getTestViewRes;
    private PostTestRes postTestRes;

    private SharedPreferenceManager sharedPreferenceManager;

    private int permissioncheck = 1;
    private String[] permissionArr = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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

        int testMode = intent.getIntExtra("testMode", 1);
        sharedPreferenceManager = (SharedPreferenceManager) intent.getSerializableExtra(("shared"));

        int userIdx = sharedPreferenceManager.getUserIdx();
        new Thread(() -> {
            try {
                getTestViewRes = testService.callTestViewApi(userIdx, testMode);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //View Element들 가져오기
        HeaderText = (TextView)findViewById(R.id.TestHeaderTxt);
        HeaderDesc = (TextView)findViewById(R.id.TestDescTxt);
        TestNumText = (TextView)findViewById(R.id.TestNumTxt);

        getFileBtn = (Button)findViewById(R.id.GetFileBtn);

        getFileBtn.setOnClickListener(view -> {
            //TODO: 로컬 파일 가져오기
        });

        wranTxt1 = (TextView)findViewById(R.id.warnTxt1);
        wranTxt2 = (TextView)findViewById(R.id.warnTxt2);
        wranTxt3 = (TextView)findViewById(R.id.warnTxt3);
        wranTxt4 = (TextView)findViewById(R.id.warnTxt4);
        wranTxt5 = (TextView)findViewById(R.id.warnTxt5);

        TestStartBtn = (Button)findViewById(R.id.ConductTestBtn);

        String[] warnList;

        //영상 테스트일때
        if(testMode == 1){
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
        // 실험 횟수 텍스트 세팅
        TestNumText.setText(getTestViewRes.getTestCount() + "회차");

        //주의사항 텍스트 세팅
        wranTxt1.setText(warnList[0]);
        wranTxt2.setText(warnList[1]);
        wranTxt3.setText(warnList[2]);
        wranTxt4.setText(warnList[3]);
        wranTxt5.setText(warnList[4]);

        if(!hasPermissions(this, permissionArr)){
            ActivityCompat.requestPermissions(this, permissionArr, permissioncheck);
        }

        TestStartBtn.setOnClickListener(view -> {
            Log.d("Testing", "Test Start");
            //테스트 API 바인딩
            new Thread(() -> {
                try {
                    postTestRes = testService.callTestApi(sharedPreferenceManager.getUserIdx(), testMode);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //음성이냐 영상이냐에 따라서 인플레이트 다르게
            Intent intent1 = new Intent(TestingActivity.this, ResultActivity.class);
            if(testMode == 0){ //음성
                intent1.putExtra("testMode", "VOICE");

            }
            else{
                intent1.putExtra("testMode", "VIDEO");

            }
            intent1.putExtra("testIdx", postTestRes.getTestIdx());
            startActivity(intent1);
        });
    }

    public boolean hasPermissions(Context context, String...permissionArr){
        if(context != null && permissionArr !=null){
            for(String permission : permissionArr){
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }
    private void getPermission(){
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(Build.VERSION.SDK_INT >= 23){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("permission", permissionArr[0] + "was " + grantResults[0]);
                recreate();
            }
            else
            {
                Log.d("permission", "denied");
                Toast.makeText(TestingActivity.this, "앱을 사용하기 위해서는 메모리 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                getPermission();
            }
        }
    }
}