package com.example.myapp.activity;

import com.airbnb.lottie.LottieAnimationView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp.entity.LoginResponse;
import com.example.myapp.util.StringUtils;

import com.example.myapp.R;
import com.example.myapp.api.Api;
import com.example.myapp.api.ApiConfig;
import com.example.myapp.api.TtitCallback;
import com.example.myapp.util.StringUtils;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity {
    private Button btnLogin;
    private EditText etAccount, etPassword;
    private Object String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_go);
        etAccount = findViewById(R.id.et_login_account);
        etPassword = findViewById(R.id.et_login_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                login(account, password);
            }
        });
    }

    private void login(String account, String password) {
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
        Api.config(ApiConfig.LOGIN, params).postRequest(this, new TtitCallback() {
            @Override
            public void onSuccess(final String res) {
                Log.e("onSuccess", res);
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(res, LoginResponse.class);
                if (loginResponse.getCode() == 0) {//为0登录成功，得到token
                    String token = loginResponse.getToken();
//                    SharedPreferences sharedPreferences = getSharedPreferences("sp_example", MODE_PRIVATE);//将token保存到本地
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("token", token);
//                    editor.commit();
                    saveStringToSp("token", token);
                    showToastSync("登陆成功");
                    LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.lottie_login);
                    animationView.playAnimation();//播放动画
                } else {
                    showToastSync("登陆失败");
                }
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }
}
