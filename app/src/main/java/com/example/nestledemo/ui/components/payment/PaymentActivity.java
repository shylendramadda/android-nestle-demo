package com.example.nestledemo.ui.components.payment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.ui.components.coffeedispense.CoffeeDispenseActivity;
import com.example.nestledemo.ui.components.home.HomeActivity;
import com.example.nestledemo.utils.AppUtils;
import com.example.nestledemo.utils.PrefUtils;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity {

    @BindView(R.id.btnCard)
    Button btnCard;

    @BindView(R.id.btnGpay)
    Button btnGpay;

    @BindView(R.id.btnApay)
    EditText btnApay;

    @BindView(R.id.etCardNumber)
    EditText etCardNumber;

    @BindView(R.id.etCardHolderName)
    EditText etCardHolderName;

    @BindView(R.id.etExpiresIn)
    EditText etExpiresIn;

    @BindView(R.id.etCVV)
    EditText etCVV;

    @BindView(R.id.btnProceed)
    Button btnProceed;

    private int Success;
    private String CardNumber;
    private String CardName;
    private int Expiry;
    private int CVV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PrefUtils.init(this);
    }

    @OnClick(R.id.btnProceed)
    void onButtonClicked() {
        CardNumber = etCardNumber.getText().toString();
        if (CardNumber.isEmpty()) {
            AppUtils.showToast(this, "Please Enter Card Number");
        } else if (CardNumber.length() != 16) ;
        {
            AppUtils.showToast(this, "Please Enter valid Card Number");
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_payment;
    }
}
