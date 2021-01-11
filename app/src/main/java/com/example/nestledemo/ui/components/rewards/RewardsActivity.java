package com.example.nestledemo.ui.components.rewards;

import android.content.Intent;
import android.os.Bundle;

import com.example.nestledemo.BuildConfig;
import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;

import butterknife.OnClick;

public class RewardsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btnShare)
    void onShareClick() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Use this code to earn points NES_12345_ABCD. Hey check out my app Nestle at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_rewards;
    }
}