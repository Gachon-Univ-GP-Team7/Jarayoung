package com.example.ourapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication.Adapter.VideoListAdapter;
import com.example.ourapplication.Adapter.VoiceLsitAdapter;
import com.example.ourapplication.Data.Test.GetTestListRes;
import com.example.ourapplication.Data.Test.TestList;
import com.example.ourapplication.Service.TestService;
import com.example.ourapplication.Utils.SharedPreferenceManager;

import java.io.IOException;
import java.util.List;

public class MypagelistFragment extends Fragment {
    private TestService testService = new TestService();

    private SharedPreferenceManager sharedPreferenceManager;
    private GetTestListRes getTestListRes;

    public MypagelistFragment() {
    }

    public static MypagelistFragment newInstance(SharedPreferenceManager sharedPreferenceManager) {
        MypagelistFragment fragment = new MypagelistFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mypagelist, container, false);

        new Thread(() -> {
            try {
                getTestListRes = testService.callTestListApi(sharedPreferenceManager.getUserIdx());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(!getTestListRes.getVoiceTestList().isEmpty()){
            List<TestList> voiceTestList = getTestListRes.getVoiceTestList();
            RecyclerView voiceRecyclerView = rootView.findViewById(R.id.voiceRecycle);
            LinearLayoutManager linearLayoutManagerVoice = new LinearLayoutManager(getActivity());
            voiceRecyclerView.setLayoutManager(linearLayoutManagerVoice);
            VoiceLsitAdapter voiceListAdapter = new VoiceLsitAdapter((MainActivity) getActivity(), voiceTestList);
            voiceRecyclerView.setAdapter(voiceListAdapter);

        }
        if(!getTestListRes.getVideoTestList().isEmpty()){
            List<TestList> videoTestList = getTestListRes.getVideoTestList();
            RecyclerView videoRecyclerView = rootView.findViewById(R.id.videoRecycle);
            LinearLayoutManager linearLayoutManagerVideo = new LinearLayoutManager(getActivity());
            videoRecyclerView.setLayoutManager(linearLayoutManagerVideo);
            VideoListAdapter videoListAdapter = new VideoListAdapter((MainActivity) getActivity(), videoTestList);
            videoRecyclerView.setAdapter(videoListAdapter);
        }

        // Inflate the layout for this fragment
        return rootView;
    }
}
