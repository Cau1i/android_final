package com.example.informationapp.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.informationapp.sql.MyRoomDatabase;
import com.example.informationapp.sql.User;
import com.example.informationapp.util.StringUtils;
import com.example.informationapp.R;

import java.util.List;

public class RegisterActivity extends BaseActivity {
    private Button btnRegister;
    private TextView tvHaveAccount;
    private EditText etAccount, etPassword;
    private MyRoomDatabase db;

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

        db = MyRoomDatabase.getInstance(mContext);
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
                finish();
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
        boolean flag = true;
        List<User> userAccount = db.userDao().selectAll();
        for (User user : userAccount) {
            if (account.equals(user.account)) {
                showToast("该用户已存在");
                flag = false;
                return;
            }
        }
        if (flag == true) {
            User user = new User();
            user.account = account;
            user.password = password;
            db.userDao().insert(user);
            showToast("账号:" + account + "密码:" + password + "注册成功");
        }
    }
}