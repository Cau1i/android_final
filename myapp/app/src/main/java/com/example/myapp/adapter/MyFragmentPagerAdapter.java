package com.example.myapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapp.fragment.CollectFragment;
import com.example.myapp.fragment.HomeFragment;
import com.example.myapp.fragment.MyFragment;

import java.util.ArrayList;

public class MyFragmentPagerAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> mFragments;
    String[] mTitles;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<Fragment> mFragments, String[] mTitles) {
        super(fragmentManager, lifecycle);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CollectFragment();
            case 2:
                return new MyFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}
