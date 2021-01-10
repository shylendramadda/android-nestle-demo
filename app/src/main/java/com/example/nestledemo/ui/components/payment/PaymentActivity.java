package com.example.nestledemo.ui.components.payment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.utils.AppUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity {

    @BindView(R.id.btnCard)
    Button btnCard;

    @BindView(R.id.btnGpay)
    Button btnGpay;

    @BindView(R.id.btnApay)
    Button btnApay;

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
    private String cardNumber;
    private String CardName;
    private int Expiry;
    private int CVV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btnProceed)
    void onButtonClicked() {
        cardNumber = etCardNumber.getText().toString();
        if (cardNumber.isEmpty()) {
            AppUtils.showToast(this, "Please Enter Card Number");
        } else if (cardNumber.length() != 16) {
            AppUtils.showToast(this, "Please Enter valid Card Number");
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_payment;
    }
}
