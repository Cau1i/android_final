package com.example.myapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.adapter.VideoAdapter;
import com.example.myapp.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {
    private String title;

    public VideoFragment() {
    }

    public static VideoFragment newInstance(String title) {
        VideoFragment fragment = new VideoFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<VideoEntity> datas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setAuthor("大胃王");
            videoEntity.setTitle("韭菜饺子新做法，不发面不发黄");
            videoEntity.setLikeCount(i * 2);
            videoEntity.setCommentCount(i * 4);
            videoEntity.setCollectCount(i * 6);
            datas.add(videoEntity);
        }
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), datas);
        recyclerView.setAdapter(videoAdapter);
        return view;
    }
}