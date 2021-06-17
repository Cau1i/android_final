package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.myapp.R;
import com.example.myapp.adapter.MyFragmentPagerAdapter;
import com.example.myapp.fragment.CollectFragment;
import com.example.myapp.fragment.HomeFragment;
import com.example.myapp.fragment.MyFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager2 viewPager2;
    private MyFragmentPagerAdapter pagerAdapter;
    private RadioButton rb_home, rb_search, rb_my, rb_current;

    private String[] mTitles = {"首页", "搜索", "我的"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initPager();
        initTabView();
    }

    //管理Fragment
    private void initPager() {
        viewPager2 = findViewById(R.id.viewpager2);
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(CollectFragment.newInstance());
        mFragments.add(MyFragment.newInstance());

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), mFragments, mTitles);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.setUserInputEnabled(false);//设置禁止滑动

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }
        });
    }

    //底部UI管理
    private void initTabView() {
        rb_home = findViewById(R.id.tab_home);
        rb_search = findViewById(R.id.tab_search);
        rb_my = findViewById(R.id.tab_mine);

        rb_home.setOnClickListener(this);
        rb_search.setOnClickListener(this);
        rb_my.setOnClickListener(this);

        rb_home.setSelected(false);
        rb_current = rb_home;
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
            case R.id.tab_search:
                viewPager2.setCurrentItem(1, false);
            case 1:
                rb_search.setSelected(true);
                rb_current = rb_search;
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