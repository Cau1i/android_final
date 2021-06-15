package com.example.myapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapp.R;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_go);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.lottie_login);
//                animationView.setAnimation("game_console_animation");//在assets目录下的动画json文件名。
//                animationView.setImageAssetsFolder("images/");//assets目录下的子目录，存放动画所需的图片
                animationView.playAnimation();//播放动画
            }
        });
    }
}