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

    private String selectedSize = "small";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedSize = getIntent().getStringExtra("selectedSize");
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_promo;
    }

    @OnClick(R.id.btnIncrease)
    void onReferClick() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app Nestle at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @OnClick(R.id.iBCrossAdd)
    void onButtonAdd(){
        Intent addIntent = new Intent();
        Toast.makeText(getApplicationContext(),"You have availed this Promo",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.ibDiscount)
    void onButtonDisc(){
        Intent onButtonDisc = new Intent();
        Toast.makeText(getApplicationContext(),"You have availed this Promo",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnSkipPromo)
    void onSkip() {
        Toast.makeText(getApplicationContext(), "You have skipped the Promo", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, PaymentActivity.class)
        .putExtra("selectedSize", selectedSize));
    }

    @OnClick(R.id.btnAdd)
    void onProceed() {
        Toast.makeText(getApplicationContext(), "You have added the Promo", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, PaymentActivity.class)
                .putExtra("selectedSize", selectedSize));
    }
}