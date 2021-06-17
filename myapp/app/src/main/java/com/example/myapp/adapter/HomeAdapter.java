package com.example.myapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragment;
    private String[] mTitles;

    public HomeAdapter(FragmentManager fragmentManager, String[] mTitles, ArrayList<Fragment> mFragment) {
        super(fragmentManager);
        this.mFragment = mFragment;
        this.mTitles = mTitles;
    }

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
        return mTitles[position];
    }
}
