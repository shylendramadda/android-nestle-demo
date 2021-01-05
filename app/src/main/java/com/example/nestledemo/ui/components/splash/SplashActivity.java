package com.example.nestledemo.ui.components.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.ui.components.home.HomeActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long SPLASH_DELAY = 2000L;
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // This will call after 2 seconds
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }, SPLASH_DELAY); // 2 seconds

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }
}