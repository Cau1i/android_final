package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.api.Api;
import com.example.myapp.api.ApiConfig;
import com.example.myapp.api.TtitCallback;
import com.example.myapp.util.StringUtils;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity {
    private Button btnRegister;
    private TextView tvHaveAccount;
    private EditText etAccount, etPassword;
    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        btnRegister = findViewById(R.id.btn_register);
        tvHaveAccount = findViewById(R.id.tv_have_account);
        etAccount = findViewById(R.id.et_register_account);
        etPassword = findViewById(R.id.et_register_password);
    }

    @Override
    protected void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                register(account, password);
            }
        });
        tvHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(LoginActivity.class);
            }
        });
    }

    private void register(String account, String password) {
        if (StringUtils.isEmpty(account)) {
            showToast("请输入账号");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            showToast("请输入密码");
            return;
        }

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("mobile", account);
        params.put("password", password);
        Api.config(ApiConfig.REGISTER, params).postRequest(this, new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                Log.e("onSuccess", res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(res);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("onFailure", e.toString());
            }
        });
    }
}