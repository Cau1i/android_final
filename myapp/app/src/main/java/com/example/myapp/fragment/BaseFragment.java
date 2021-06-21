package com.example.myapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dueeeke.videoplayer.player.VideoViewManager;

import static android.content.Context.MODE_PRIVATE;

public abstract class BaseFragment extends Fragment {
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(initLayout(), container, false);
            initView();
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int initLayout();

    //封装Toast
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    //封装ToastSync
    public void showToastSync(String msg) {
        Looper.prepare();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    //封装Intent
    public void navigateTo(Class cls) {
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
    }

    //封装带参Intent
    public void navigateToWithBundle(Class cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //封装Intent （堆栈）
    public void navigateToWithFlag(Class cls, int flags) {
        Intent intent = new Intent(getActivity(), cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //插入
    protected void insertVal(String key, String val) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sp_example", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, val);
        editor.commit();
    }

    //查询
    protected String findByKey(String key) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sp_example", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    //删除封装SharedPreferences的key
    protected void removeByKey(String key) {
        SharedPreferences sp = getActivity().getSharedPreferences("sp_example", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.remove(key);
        edit.commit();
    }

    protected VideoViewManager getVideoViewManager() {
        return VideoViewManager.instance();
    }
}



