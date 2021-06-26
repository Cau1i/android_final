package com.example.informationapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.informationapp.entity.NewsTab;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mFragment;
    private List<NewsTab> newsTabList;

    public NewsListAdapter(FragmentManager fragmentManager, List<NewsTab> newsTabList, ArrayList<Fragment> mFragment) {
        super(fragmentManager);
        this.mFragment = mFragment;
        this.newsTabList = newsTabList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        NewsTab newsTab =newsTabList.get(position);
        String titleTypeTab = newsTab.getType();
        return titleTypeTab;
    }
}
