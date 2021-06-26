package com.example.informationapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.informationapp.R;
import com.example.informationapp.adapter.NewsListAdapter;
import com.example.informationapp.entity.NewsTab;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NewsListAdapter mNewsListAdapter;
    private ArrayList<Fragment> mFragments;//viewpager所显示的内容
    private List<NewsTab> mDatas;//所选中的类型的集合

    public NewsListFragment() {
    }

    public static NewsListFragment newInstance() {
        NewsListFragment fragment = new NewsListFragment();
        return fragment;
    }

    @Override
    protected void initView() {
        mViewPager = mRootView.findViewById(R.id.news_list_view);
        mTabLayout = mRootView.findViewById(R.id.news_tab_layout);
        mFragments = new ArrayList<>();
        mDatas = new ArrayList<>();
    }

    @Override
    protected void initData() {
        //初始化顶部Tab
        List<NewsTab> allNewsTabList = NewsTab.getNewsTabList();
        mDatas.addAll(allNewsTabList);
        for (int i = 0; i < mDatas.size(); i++) {
            NewsTab newsTab = mDatas.get(i);//得到每一个栏目的对象
            NewsFragment newsFragment = new NewsFragment();
            //向fragment中传递数据
            Bundle bundle = new Bundle();
            bundle.putSerializable("type", newsTab);
            newsFragment.setArguments(bundle);
            mFragments.add(newsFragment);
//            mFragments.add(NewsFragment.newInstance(mDatas.get(i).getUrl()));//构造函数的方式传递数据
        }
        //创建适配器对象
        mNewsListAdapter = new NewsListAdapter(getActivity().getSupportFragmentManager(), mDatas, mFragments);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragments.size());
        //设置适配器
        mViewPager.setAdapter(mNewsListAdapter);
        //关联TabLayout和ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_news_list;
    }
}
