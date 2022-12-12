package com.example.ourapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MypagelistFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button voice1;
    private Button voice2;
    private Button voice3;
    private Button video1;
    private Button video2;
    private Button video3;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MypagelistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mypagelist, container, false);

        voice1 = rootView.findViewById(R.id.voice1);
        voice2 = rootView.findViewById(R.id.voice2);
        voice3 = rootView.findViewById(R.id.voice3);
        video1 = rootView.findViewById(R.id.video1);
        video2 = rootView.findViewById(R.id.video2);
        video3 = rootView.findViewById(R.id.video3);

        voice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("testMode", "VOICE");
                intent.putExtra("testDate", voice1.getText());
                startActivity(intent);
            }
        });

        voice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("testMode", "VOICE");
                intent.putExtra("testDate", voice2.getText());
                startActivity(intent);
            }
        });

        voice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("testMode", "VOICE");
                intent.putExtra("testDate", voice3.getText());
                startActivity(intent);
            }
        });

        video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("testMode", "VIDEO");
                intent.putExtra("testDate", video1.getText());
                startActivity(intent);
            }
        });

        video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("testMode", "VIDEO");
                intent.putExtra("testDate", video2.getText());
                startActivity(intent);
            }
        });

        video3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("testMode", "VIDEO");
                intent.putExtra("testDate", video3.getText());
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
}
