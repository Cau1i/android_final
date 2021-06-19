package com.example.myapp.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.R;
import com.example.myapp.adapter.HomeAdapter;
import com.example.myapp.util.StringUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private HomeAdapter mHomeAdapter;
    private ArrayList<Fragment> mFragments;
    private String[] mTitles = {"首页", "资讯", "测评", "节目", "攻略", "专栏", "历史"};


    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mViewPager = mRootView.findViewById(R.id.fixedViewPager);
        mTabLayout = mRootView.findViewById(R.id.tab_layout);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();

        for (String title : mTitles) {
            mFragments.add(VideoFragment.newInstance(title));
        }

        mHomeAdapter = new HomeAdapter(getActivity().getSupportFragmentManager(), mTitles, mFragments);
        mViewPager.setOffscreenPageLimit(mFragments.size());//预加载
        mViewPager.setAdapter(mHomeAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}