package com.example.informationapp.activity;

import android.animation.Animator;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.informationapp.sql.MyRoomDatabase;
import com.example.informationapp.sql.User;
import com.example.informationapp.util.StringUtils;
import com.example.informationapp.R;
import com.google.gson.Gson;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button btnLogin;
    private LottieAnimationView animationView;
    private EditText etAccount, etPassword;
    private CheckBox cbRemember;
    private TextView tvCreatAnAccount;
    private String account, password;
    private MyRoomDatabase db;

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
        tvCreatAnAccount = findViewById(R.id.tv_create_an_account);
        animationView = findViewById(R.id.lottie_login);

        btnLogin.setOnClickListener(this::onClick);
        tvCreatAnAccount.setOnClickListener(this::onClick);

        db = MyRoomDatabase.getInstance(mContext);
    }

    @Override
    protected void initData() {
        if (getBooleanByKey("checkboxBoolean", false)) {
            cbRemember.setChecked(true);
            etAccount.setText(findByKey("account"));
            etPassword.setText(findByKey("password"));
        }
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
        List<User> userAccount = db.userDao().selectAll();
        for (User user : userAccount) {
            if (account.equals(user.account) && password.equals(user.password)) {
                showToast("登录成功");
                insertVal("logged", "已登录");
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
                return;
            }
        }
        db.close();
        showToast("账号或者密码错误");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
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
                break;
            case R.id.tv_create_an_account:
                navigateTo(RegisterActivity.class);
                break;
        }
    }
}
