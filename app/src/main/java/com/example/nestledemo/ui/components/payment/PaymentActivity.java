package com.example.nestledemo.ui.components.payment;

import android.os.Bundle;

import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;

import butterknife.OnClick;

public class PaymentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_payment;
    }

    @OnClick(R.id.btnProceed)
    void onProceed() {

    }
}