package com.example.myapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initData();
    }

    protected abstract int initLayout();//获取布局文件

    protected abstract void initView();//获取控件对象

    protected abstract void initData();//事件监听

    //封装Toast
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    //封装ToastSync
    public void showToastSync(String msg) {
        Looper.prepare();
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    ///封装Intent
    public void navigateTo(Class cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }

    //封装SharedPreferences存储信息
    protected void saveStringToSp(String key, String val) {
        SharedPreferences sharedPreferences = getSharedPreferences("sp_example", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, val);
        editor.commit();
    }

    //封装SharedPreferences得到信息
    protected String getStringFromSP(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("sp_example", MODE_PRIVATE);
        return sharedPreferences.getString(key, "");

    }

    protected boolean getBooleanFromSP(String key, boolean flag) {
        SharedPreferences sharedPreferences = getSharedPreferences("sp_example", MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, flag);
    }

    protected void putBooleanFromSP(String key, boolean flag) {
        SharedPreferences sharedPreferences = getSharedPreferences("sp_example", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, flag);
        editor.commit();
    }
}
