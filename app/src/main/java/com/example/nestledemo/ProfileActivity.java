package com.example.nestledemo;

import android.os.Bundle;

import com.example.nestledemo.ui.components.base.BaseActivity;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile;
    }

}