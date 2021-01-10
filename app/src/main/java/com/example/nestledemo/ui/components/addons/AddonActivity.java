package com.example.nestledemo.ui.components.addons;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.nestledemo.BuildConfig;
import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.ui.components.payment.PaymentActivity;

import butterknife.OnClick;

public class AddonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_addon;
    }

    @OnClick(R.id.referLL)
    void onReferClick() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app Nestle at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @OnClick(R.id.btnSkip)
    void onSkip()
    {
        Intent skipToPay = new Intent();
        skipToPay.setClass(this,PaymentActivity.class);
        Toast.makeText(getApplicationContext(),"You have skipped the UpSale",Toast.LENGTH_LONG).show();
        startActivity(skipToPay);
    }

    @OnClick(R.id.btnProceed)
    void onProceed()
    {
        Intent proceedToPay = new Intent();
        proceedToPay.setClass(this,PaymentActivity.class);
        Toast.makeText(getApplicationContext(),"You have added the UpSale",Toast.LENGTH_LONG).show();
        startActivity(proceedToPay);

    }
}