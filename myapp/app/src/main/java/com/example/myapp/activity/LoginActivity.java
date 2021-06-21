package com.example.myapp.activity;

import com.airbnb.lottie.LottieAnimationView;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private CheckBox cbRemember;
    private EditText etAccount, etPassword;
    private LottieAnimationView animationView;
    private String account;
    private String password;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        btnLogin = findViewById(R.id.btn_go);
        cbRemember = findViewById(R.id.cb_remember);
        etAccount = findViewById(R.id.et_login_account);
        etPassword = findViewById(R.id.et_login_password);
        animationView = findViewById(R.id.lottie_login);
    }

    @Override
    protected void initData() {
        if (getBooleanByKey("checkboxBoolean", false)) {
            cbRemember.setChecked(true);
            etAccount.setText(findByKey("account"));
            etPassword.setText(findByKey("password"));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = etAccount.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                //按钮被选中，下次进入时会显示账号和密码
                //按钮被选中，清空账号和密码，下次进入时会显示账号和密码为空
                if (cbRemember.isChecked()) {
                    insertVal("account", account);//将账号保存到本地
                    insertVal("password", password);//将密码保存到本地
                    putBooleanByKey("checkboxBoolean", true);
                } else {
                    insertVal("account", null);
                    insertVal("password", null);
                    putBooleanByKey("checkboxBoolean", false);
                }
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
                    insertVal("token", token);//将token存储到本地
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            animationView.playAnimation();//播放动画
                            animationView.addAnimatorListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    navigateToWithFlag(HomeActivity.class,
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//跳转到主页 清除其他页面
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            });
                        }
                    });
                    showToastSync("登录成功");
                } else {
                    showToastSync("登录失败");
                }
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }
}
