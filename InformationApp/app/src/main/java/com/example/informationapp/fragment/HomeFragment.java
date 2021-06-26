package com.example.informationapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.informationapp.R;
import com.example.informationapp.adapter.HomeAdapter;
import com.example.informationapp.api.VideoURL;
import com.example.informationapp.entity.NewsTab;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private ViewPager mViewPager;
    private HomeAdapter mHomeAdapter;
    private ArrayList<Fragment> mFragments;//viewpager所显示的内容

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected void initView() {
        mViewPager = mRootView.findViewById(R.id.video_list_view);
        mFragments = new ArrayList<>();
    }

    @Override
    protected void initData() {
        VideoFragment videoFragment = new VideoFragment();
        //向fragment中传递数据
        Bundle bundle = new Bundle();
        bundle.putString("url", VideoURL.hot_video_url);
        videoFragment.setArguments(bundle);
        mFragments.add(videoFragment);
        mHomeAdapter = new HomeAdapter(getActivity().getSupportFragmentManager(), mFragments);
        mViewPager.setOffscreenPageLimit(mFragments.size());//预加载
        mViewPager.setAdapter(mHomeAdapter);
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }
}
