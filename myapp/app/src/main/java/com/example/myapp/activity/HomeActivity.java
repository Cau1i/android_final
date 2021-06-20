package com.example.myapp.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.View;
import android.widget.RadioButton;

import com.example.myapp.R;
import com.example.myapp.adapter.MyFragmentStateAdapter;
import com.example.myapp.fragment.CollectFragment;
import com.example.myapp.fragment.HomeFragment;
import com.example.myapp.fragment.MyFragment;
import com.example.myapp.fragment.NewsFragment;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager2 viewPager2;
    private MyFragmentStateAdapter myFragmentStateAdapter;
    private RadioButton rb_home, rb_news, rb_my, rb_current;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        rb_home = findViewById(R.id.tab_home);
        rb_news = findViewById(R.id.tab_news);
        rb_my = findViewById(R.id.tab_mine);

        rb_home.setOnClickListener(this);
        rb_news.setOnClickListener(this);
        rb_my.setOnClickListener(this);

        rb_home.setSelected(false);
        rb_current = rb_home;
    }

    @Override
    protected void initData() {
        viewPager2 = findViewById(R.id.viewpager2);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(NewsFragment.newInstance());
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
        //rb_current为当前的按钮
        rb_current.setSelected(false);
        switch (position) {
            case R.id.tab_home:
                viewPager2.setCurrentItem(0, false);
            case 0:
                rb_home.setSelected(true);
                rb_current = rb_home;
                break;
            case R.id.tab_news:
                viewPager2.setCurrentItem(1, false);
            case 1:
                rb_news.setSelected(true);
                rb_current = rb_news;
                break;
            case R.id.tab_mine:
                viewPager2.setCurrentItem(2, false);
            case 2:
                rb_my.setSelected(true);
                rb_current = rb_my;
                break;
        }
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }
}