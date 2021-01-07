package com.example.nestledemo.ui.components.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.home.HomeActivity;
import com.example.nestledemo.ui.components.login.LoginActivity;
import com.example.nestledemo.utils.PrefUtils;

import static com.example.nestledemo.utils.PrefUtils.IS_LOGIN;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PrefUtils.init(this);

        long SPLASH_DELAY = 2000L;
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // This will call after 2 seconds
            boolean isLogin = PrefUtils.read(IS_LOGIN, false);
            if (isLogin) {
                startActivity(new Intent(this, HomeActivity.class));
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
            finish();
        }, SPLASH_DELAY); // 2 seconds

    }
}