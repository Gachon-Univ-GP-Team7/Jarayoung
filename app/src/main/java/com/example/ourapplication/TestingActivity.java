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
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ourapplication.Data.Test.GetTestViewRes;
import com.example.ourapplication.Data.Test.PostTestRes;
import com.example.ourapplication.Service.TestService;
import com.example.ourapplication.Utils.SharedPreferenceManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

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

    private TextView getFileTxt;
    private Button getFileBtn;
    private TextView wranTxt1;
    private TextView wranTxt2;
    private TextView wranTxt3;
    private TextView wranTxt4;
    private TextView wranTxt5;
    private Button TestStartBtn;

    private TextView fileMakeTxt;

    private Button fileMakeBtn;

    private EditText filePathTxt;

    private MediaRecorder recorder;
    private String filename;
    private final String externalStorageDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final String directoryPath = externalStorageDir + File.separator + "Jarayoung";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        getPermission();

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
        getFileTxt = (TextView) findViewById(R.id.GetFileTxt);

        //파일 경로 표시 에딧 텍스트
        filePathTxt = (EditText) findViewById(R.id.FilePathEditText);

        getFileBtn.setOnClickListener(view -> {
            //TODO: 음성 녹음해서 파일 로드하기--
        });

        fileMakeTxt = (TextView) findViewById(R.id.FileMakeTxt);
        //파일 녹음/녹화 버튼
        fileMakeBtn = (Button) findViewById(R.id.MakeFileBtn);

        // 파일 저장 디랙토리
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        final boolean[] isRecord = {false};
        fileMakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRecord[0]){
                    isRecord[0] = false;
                    Toast.makeText(getApplicationContext(), "Record Stop", Toast.LENGTH_LONG).show();
                    stopRecording();
                }
                else {
                    isRecord[0] = true;
                    Toast.makeText(getApplicationContext(), "Record Start", Toast.LENGTH_LONG).show();
                    recordAudio();
                }
            }
        });

        //주의사항 텍스트 가져오기
        wranTxt1 = (TextView)findViewById(R.id.warnTxt1);
        wranTxt2 = (TextView)findViewById(R.id.warnTxt2);
        wranTxt3 = (TextView)findViewById(R.id.warnTxt3);
        wranTxt4 = (TextView)findViewById(R.id.warnTxt4);
        wranTxt5 = (TextView)findViewById(R.id.warnTxt5);

        //테스트 시작 버튼
        TestStartBtn = (Button)findViewById(R.id.ConductTestBtn);

        String[] warnList;

        //영상 테스트일때
        if(testMode == 1){
            warnList = getResources().getStringArray(R.array.video_warn_list);
            HeaderText.setText(getResources().getString(R.string.move_test_header));
            HeaderDesc.setText(getResources().getString(R.string.move_test_desc));
            getFileTxt.setText(getResources().getString(R.string.get_video_txt));
            fileMakeTxt.setText(getResources().getString(R.string.video_make_txt));
            fileMakeBtn.setText("촬영하기");

        }
        //음성 테스트일때
        else {
            warnList = getResources().getStringArray(R.array.voice_warn_list);
            HeaderText.setText(getResources().getString(R.string.voice_test_header));
            HeaderDesc.setText(getResources().getString(R.string.voice_test_desc));
            getFileTxt.setText(getResources().getString(R.string.get_voice_txt));
            fileMakeTxt.setText(getResources().getString(R.string.voice_make_txt));
            fileMakeBtn.setText("녹음하기");
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

        //테스트 시작 버튼
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

    /**
     * 권한
     * */
    private void getPermission(){
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(Build.VERSION.SDK_INT >= 23){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("permission", permissionArr[0] + "was " + grantResults[0]);
//                recreate();
            }
            else
            {
                Log.d("permission", "denied");
                Toast.makeText(TestingActivity.this, "앱을 사용하기 위해서는 메모리 접근과 음성 녹음 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 파일 레코딩 시작/멈춤
     * */
    public void recordAudio() {

        String fileName = "Jarayoung_Voice_" + System.currentTimeMillis() + ".mp3"; // 파일 확장자는 녹음 형식에 따라 조정
        String filePath = directoryPath + File.separator + fileName;

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(filePath);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        try {
            recorder.prepare();     // recorder 시작
            recorder.start();
            Log.d("Record", "recordAudio: ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        filePathTxt.setText(filePath);
    }

    public void stopRecording() {
        try {
            if (recorder != null) {
                recorder.stop();
                recorder.reset();
                recorder.release();
                recorder = null;

            }
        }catch (Exception exception){
            Log.e("Record", "stopRecording: " + exception.getMessage());
        }

    }
}