package com.example.informationapp.activity;

import android.view.View;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.informationapp.R;
import com.example.informationapp.adapter.MyFragmentStateAdapter;
import com.example.informationapp.fragment.HomeFragment;
import com.example.informationapp.fragment.MyFragment;
import com.example.informationapp.fragment.NewsListFragment;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager2 viewPager2;
    private RadioButton rbHome, rbNews, rbMy, rbCurrent;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyFragmentStateAdapter myFragmentStateAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        rbHome = findViewById(R.id.tab_home);
        rbNews = findViewById(R.id.tab_news);
        rbMy = findViewById(R.id.tab_mine);

        rbHome.setOnClickListener(this);
        rbNews.setOnClickListener(this);
        rbMy.setOnClickListener(this);

        rbHome.setSelected(false);
        rbCurrent = rbHome;
    }

    @Override
    protected void initData() {
        viewPager2 = findViewById(R.id.viewpager2);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(NewsListFragment.newInstance());
        mFragments.add(MyFragment.newInstance());

        myFragmentStateAdapter = new MyFragmentStateAdapter(getSupportFragmentManager(), getLifecycle(), mFragments);

        viewPager2.setAdapter(myFragmentStateAdapter);
        viewPager2.setUserInputEnabled(false);//设置禁止滑动

        viewPager2.setOffscreenPageLimit(mFragments.size());//预加载

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }
        });
    }

    private void changeTab(int position) {
        //rbCurrent为当前的按钮
        rbCurrent.setSelected(false);
        switch (position) {
            case R.id.tab_home:
                viewPager2.setCurrentItem(0, false);
            case 0:
                rbHome.setSelected(true);
                rbCurrent = rbHome;
                break;
            case R.id.tab_news:
                viewPager2.setCurrentItem(1, false);
            case 1:
                rbNews.setSelected(true);
                rbCurrent = rbNews;
                break;
            case R.id.tab_mine:
                viewPager2.setCurrentItem(2, false);
            case 2:
                rbMy.setSelected(true);
                rbCurrent = rbMy;
                break;
        }
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }
}