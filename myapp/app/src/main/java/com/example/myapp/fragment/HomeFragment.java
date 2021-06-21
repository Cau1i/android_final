package com.example.myapp.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.R;
import com.example.myapp.adapter.HomeAdapter;
import com.example.myapp.api.Api;
import com.example.myapp.api.ApiConfig;
import com.example.myapp.api.TtitCallback;
import com.example.myapp.entity.CategoryEntity;
import com.example.myapp.entity.VideoCategoryResponse;
import com.example.myapp.util.StringUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private HomeAdapter mHomeAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    ;
    private String[] mTitles;


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
        getVideoCategoryList();
    }

    //视频类型tab接口
    private void getVideoCategoryList() {
            HashMap<String, Object> params = new HashMap<>();
            Api.config(ApiConfig.VIDEO_CATEGORY_LIST, params).getRequest(getActivity(), new TtitCallback() {
                @Override
                public void onSuccess(final String res) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            VideoCategoryResponse response = new Gson().fromJson(res, VideoCategoryResponse.class);
                            if (response != null && response.getCode() == 0) {
                                List<CategoryEntity> list = response.getPage().getList();
                                if (list != null && list.size() > 0) {
                                    mTitles = new String[list.size()];
                                    for (int i = 0; i < list.size(); i++) {
                                        mTitles[i] = list.get(i).getCategoryName();
                                        mFragments.add(VideoFragment.newInstance(list.get(i).getCategoryId()));
                                    }
                                    mHomeAdapter = new HomeAdapter(getActivity().getSupportFragmentManager(), mTitles, mFragments);
                                    mViewPager.setOffscreenPageLimit(mFragments.size());//预加载
                                    mViewPager.setAdapter(mHomeAdapter);
                                    mTabLayout.setupWithViewPager(mViewPager);
                                }
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Exception e) {
                }
            });
    }
}