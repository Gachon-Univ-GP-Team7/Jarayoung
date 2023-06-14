package com.example.ourapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ourapplication.Data.Test.GetVideoDetailRes;
import com.example.ourapplication.Data.Test.GetVoiceDetailRes;
import com.example.ourapplication.Service.TestService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.io.IOException;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TestService testService = new TestService();
    private TextView typeTxt;
    private TextView dateTxt;
    private BarChart barChart;

    private GetVoiceDetailRes getVoiceDetailRes;
    private GetVideoDetailRes getVideoDetailRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //인텐트에서 Extra 가져오기
        Intent intent = getIntent();
        String testMode = intent.getStringExtra("testMode");
        int testIdx = intent.getIntExtra("testIdx", 1);

        typeTxt = (TextView)findViewById(R.id.typeTxt);
        dateTxt = (TextView)findViewById(R.id.dateTxt);

        if(testMode.equalsIgnoreCase("VIDEO")){
            new Thread(() -> {
                try {
                    getVideoDetailRes = testService.callVideoDetailApi(testIdx);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            typeTxt.setText("검사분야 : 행동발달");
            dateTxt.setText("검사일자 : " + getVideoDetailRes.getTestDate().split(" ")[0]);

            drawChart(getVideoDetailRes.getOverallScore());
        }
        else{
            new Thread(() -> {
                try {
                    getVoiceDetailRes = testService.callVoiceDetailApi(testIdx);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            typeTxt.setText("검사분야 : 언어발달");
            dateTxt.setText("검사일자 : " + getVoiceDetailRes.getTestDate().split(" ")[0]);

            drawChart(getVoiceDetailRes.getOverallScore());
        }


    }

    private void drawChart(float overallScore){
        ArrayList<BarEntry> entry_chart = new ArrayList<>(); // 데이터를 담을 Arraylist

        barChart = (BarChart) findViewById(R.id.resultChart);

        BarData barData = new BarData(); // 차트에 담길 데이터

        entry_chart.add(new BarEntry(1, overallScore)); //entry_chart1에 좌표 데이터를 담는다.



        BarDataSet barDataSet = new BarDataSet(entry_chart, "추정 나이"); // 데이터가 담긴 Arraylist 를 BarDataSet 으로 변환한다.

        barDataSet.setColor(Color.BLUE); // 해당 BarDataSet 색 설정 :: 각 막대 과 관련된 세팅은 여기서 설정한다.

        barData.addDataSet(barDataSet); // 해당 BarDataSet 을 적용될 차트에 들어갈 DataSet 에 넣는다.

        barChart.setData(barData); // 차트에 위의 DataSet 을 넣는다.

        barChart.invalidate(); // 차트 업데이트
        barChart.setTouchEnabled(false); // 차트 터치 불가능하게
    }
}