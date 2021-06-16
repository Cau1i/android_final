package com.example.myapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    //封装Toast
    public void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    //封装ToastSync
    public void showToastSync(String msg) {
        Looper.prepare();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    ///封装Intent
    public void navigateTo(Class cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }

    //封装SharedPreferences
    protected void saveStringToSp(String key, String val) {
        SharedPreferences sharedPreferences = getSharedPreferences("sp_example", MODE_PRIVATE);//将token保存到本地
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, val);
        editor.commit();
    }
}
