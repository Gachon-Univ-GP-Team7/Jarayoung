package com.example.ourapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ourapplication.Data.Test.GetTestGraphRes;
import com.example.ourapplication.Data.Test.TestGraph;
import com.example.ourapplication.Service.TestService;
import com.example.ourapplication.Utils.SharedPreferenceManager;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MypageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MypageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private SharedPreferenceManager sharedPreferenceManager;
    private final TestService testService = new TestService();
    private GetTestGraphRes testGraphRes;

    MainActivity mainActivity;
    private ImageButton cal_btn;
    private LineChart lineChart1;
    private LineChart lineChart2;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MypageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment mypageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MypageFragment newInstance(SharedPreferenceManager sharedPreferenceManager) {
        MypageFragment fragment = new MypageFragment();
        Bundle args = new Bundle();
        args.putSerializable("shared", sharedPreferenceManager);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sharedPreferenceManager = (SharedPreferenceManager) getArguments().getSerializable("shared");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        new Thread(() -> {
            try {
                Log.d("Graph", Integer.toString(sharedPreferenceManager.getUserIdx()));

                testGraphRes = testService.callTestGraphApi(sharedPreferenceManager.getUserIdx());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        drawChart1(rootView, testGraphRes.getVoiceGraphList());
        drawChart2(rootView, testGraphRes.getVideoGraphList());

        cal_btn = rootView.findViewById(R.id.calendarImage);

        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).fragmentChange(MypagelistFragment.newInstance(sharedPreferenceManager));
            }
        });

        return rootView;
    }

    public void drawChart1(ViewGroup rootView, List<TestGraph> voiceGraphList){
        lineChart1 = (LineChart) rootView.findViewById(R.id.voiceChart);

        List<Entry> entries = new ArrayList<>();
        int idx = 1;

        for(TestGraph element : voiceGraphList){
            entries.add(new Entry(idx, element.getOverallScore()));
            idx++;
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "발달 점수");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart1.setData(lineData);

        XAxis xAxis = lineChart1.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart1.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart1.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart1.setDoubleTapToZoomEnabled(false);
        lineChart1.setDrawGridBackground(false);
        lineChart1.setDescription(description);
        lineChart1.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart1.invalidate();

    }

    public void drawChart2(ViewGroup rootView, List<TestGraph> videoGraphList){
        lineChart2 = (LineChart) rootView.findViewById(R.id.videoChart);

        List<Entry> entries = new ArrayList<>();
        int idx = 1;

        for(TestGraph element : videoGraphList){
            entries.add(new Entry(idx, element.getOverallScore()));
            idx++;
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "발달 점수");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart2.setData(lineData);

        XAxis xAxis = lineChart2.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart2.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart2.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart2.setDoubleTapToZoomEnabled(false);
        lineChart2.setDrawGridBackground(false);
        lineChart2.setDescription(description);
        lineChart2.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart2.invalidate();

    }
}