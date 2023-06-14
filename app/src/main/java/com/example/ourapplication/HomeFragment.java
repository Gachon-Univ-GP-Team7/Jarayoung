package com.example.ourapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ourapplication.Data.user.GetMainViewRes;
import com.example.ourapplication.Service.UserService;
import com.example.ourapplication.Utils.SharedPreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private SharedPreferenceManager sharedPreferenceManager;
    private final UserService userService = new UserService();

    private final GetMainViewRes[] getMainViewRes = new GetMainViewRes[1];
    private Button voiceButton;
    private Button videoButton;

    private TextView header;
    private TextView name;
    private TextView birthday;
    private TextView age;
    private TextView date;
    private TextView recentDate;
    private TextView recentResult;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(SharedPreferenceManager sharedPreferenceManager) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable("shared", sharedPreferenceManager);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sharedPreferenceManager = (SharedPreferenceManager) getArguments().getSerializable("shared");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        voiceButton = rootView.findViewById(R.id.voice_btn);
        videoButton = rootView.findViewById(R.id.video_btn);
        header = rootView.findViewById(R.id.main_header);
        name = rootView.findViewById(R.id.main_name);
        birthday = rootView.findViewById(R.id.main_birthday);
        age = rootView.findViewById(R.id.main_age);
        recentDate = rootView.findViewById(R.id.main_recent_date);
        recentResult = rootView.findViewById(R.id.main_recent_result);

        new Thread(() -> {
            try {
                getMainViewRes[0] = userService.callMainViewApi(sharedPreferenceManager.getUserIdx());
                Log.d("Home", getMainViewRes[0].toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        header.setText("안녕하세요! " + getMainViewRes[0].getUserName() + "님");
        name.setText("이름: " + getMainViewRes[0].getBabyName());
        birthday.setText("생년월일: " + getMainViewRes[0].getBabyBirthday());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String formated = formatter.format(date);
        int nowYear = Integer.parseInt(formated.split("-")[0]);
        int nowMonth = Integer.parseInt(formated.split("-")[1]);

        int targetYear = Integer.parseInt(getMainViewRes[0].getBabyBirthday().split("-")[0]);
        int targetMonth = Integer.parseInt(getMainViewRes[0].getBabyBirthday().split("-")[1]);

        int babyAge = nowYear - targetYear;

        if(nowMonth < targetMonth) babyAge--;

        age.setText("연령: 만 " + babyAge + "세");

        if(getMainViewRes[0].getLatestTestDay().equalsIgnoreCase("NULL")){
            recentDate.setText("최근 검사 일자: N/A");

            recentResult.setText("최근 검사 결과: N/A");
        }
        else{
            recentDate.setText("최근 검사 일자: " + getMainViewRes[0].getLatestTestDay());

            recentResult.setText("최근 검사 결과: 만" + getMainViewRes[0].getLatestTestResult() + "세");
        }

        voiceButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TestingActivity.class);
            intent.putExtra("testMode", 0);
            intent.putExtra("shared", sharedPreferenceManager);
            startActivity(intent);
        });

        videoButton.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), TestingActivity.class);
            intent.putExtra("testMode", 1);
            intent.putExtra("shared", sharedPreferenceManager);
            startActivity(intent);
        });
        return rootView;
    }
}