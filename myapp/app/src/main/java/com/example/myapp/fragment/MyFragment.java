package com.example.myapp.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapp.R;

public class MyFragment extends BaseFragment implements View.OnClickListener {


    private ImageView imgHeader;
    private RelativeLayout rlCollect, rlSkin, rlLogout;

    public MyFragment() {
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    protected void initView() {
        imgHeader = mRootView.findViewById(R.id.img_myHeader);
        rlCollect = mRootView.findViewById(R.id.rl_collect);
        rlSkin = mRootView.findViewById(R.id.rl_skin);
        rlLogout = mRootView.findViewById(R.id.rl_logout);


    }

    @Override
    protected void initData() {
        imgHeader.setOnClickListener(this::onClick);
        rlCollect.setOnClickListener(this::onClick);
        rlSkin.setOnClickListener(this::onClick);
        rlLogout.setOnClickListener(this::onClick);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_myHeader:
                showToast("img_header");
                break;
            case R.id.rl_collect:
//                navigateTo(CollectFragment.class);
                break;
            case R.id.rl_skin:
                showToast("rl_skin");
                break;
            case R.id.rl_logout:
                showToast("退出登录");
                break;
        }
    }
}