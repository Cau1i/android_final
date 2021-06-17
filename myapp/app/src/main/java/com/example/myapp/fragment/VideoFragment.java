package com.example.myapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
        TextView tvVideoTitle = view.findViewById(R.id.tv_video_title);
        tvVideoTitle.setText(title);
        return view;
    }
}