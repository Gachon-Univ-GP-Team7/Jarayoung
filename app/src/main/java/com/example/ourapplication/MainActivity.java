package com.example.ourapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ourapplication.Base.BaseResponse;
import com.example.ourapplication.Data.user.GetMainViewRes;
import com.example.ourapplication.Service.ExampleService;
import com.example.ourapplication.Service.UserService;
import com.example.ourapplication.Utils.NetworkModule;
import com.example.ourapplication.Utils.RetrofitInterface;
import com.example.ourapplication.Utils.SharedPreferenceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
//    private FirebaseAuth mFirebaseAuth;
    private UserService userService = new UserService();

    private SharedPreferenceManager sharedPreferenceManager;
    //프래그먼트 화면 선언
    HomeFragment homeFragment;
    MypageFragment mypageFragment;
    SettingFragment settingFragment;
    MypagelistFragment mypageListFragment;
    MypageresultFragment mypageresultFragment;

    LinearLayout body;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this.getIntent());

        sharedPreferenceManager = (SharedPreferenceManager) intent.getSerializableExtra("shared");

//        Bundle bundle = new Bundle();
//        bundle.putSerializable("shared", sharedPreferenceManager);

        setContentView(R.layout.activity_main);
        homeFragment = HomeFragment.newInstance(sharedPreferenceManager);
        mypageFragment = MypageFragment.newInstance(sharedPreferenceManager);
        mypageListFragment = MypagelistFragment.newInstance(sharedPreferenceManager);
        settingFragment = new SettingFragment();
        mypageresultFragment = new MypageresultFragment();

        //초기 화면(프래그먼트) 설정// 이거 의미 다시 찾아보기
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        //초기화
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.mypage:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mypageFragment).commit();
                        return true;
                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                        return true;
                }
                return false;
            }
        });

//        mFirebaseAuth = FirebaseAuth.getInstance();

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃하기
//                mFirebaseAuth.signOut();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //String test = exampleService.callTestApi();
    }

    public void fragmentChange(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}