package com.example.nestledemo.ui.components.payment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.ui.components.coffeedispense.CoffeeDispenseActivity;
import com.example.nestledemo.utils.AppUtils;
import com.example.nestledemo.utils.ConstantUtils;

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

    @BindView(R.id.amountTV)
    TextView amountTV;

    private String cardNumber;
    private String cardName;
    private String cardExpiry;
    private String cardCVV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String selectedSize = getIntent().getStringExtra("selectedSize");

        String amount = ConstantUtils.euroSymbol + " 1";
        if (!TextUtils.isEmpty(selectedSize)) {
            if (selectedSize.contains("Small")) {
                amount = ConstantUtils.euroSymbol + " 1";
            } else if (selectedSize.contains("Medium")) {
                amount = ConstantUtils.euroSymbol + " 2";
            } else if (selectedSize.contains("Large")) {
                amount = ConstantUtils.euroSymbol + " 3";
            }
        }
        amountTV.setText(Html.fromHtml(amount));
    }

    @OnClick(R.id.btnProceed)
    void onButtonClicked() {
        cardNumber = etCardNumber.getText().toString();
        cardName = etCardHolderName.getText().toString();
        cardExpiry = etExpiresIn.getText().toString();
        cardCVV = etCVV.getText().toString();
        if (cardNumber.isEmpty()) {
            AppUtils.showToast(this, "Please Enter Card Number");
        } else if (cardNumber.length() != 16) {
            AppUtils.showToast(this, "Please Enter valid Card Number");
        } else if (cardName.isEmpty()) {
            AppUtils.showToast(this, "Please Enter Card Holder Name");
        } else if (cardName.length() > 25) {
            AppUtils.showToast(this, "Please Enter valid Card Holder Number");
        } else if (cardExpiry.isEmpty()) {
            AppUtils.showToast(this, "Please Enter Card Expiry");
        } else if (cardExpiry.length() != 5) {
            AppUtils.showToast(this, "Please Enter valid Card Expiry");
        } else if (cardCVV.isEmpty()) {
            AppUtils.showToast(this, "Please Enter CVV");
        } else if (cardCVV.length() != 3) {
            AppUtils.showToast(this, "Please Enter valid CVV");
        } else {
            AppUtils.showToast(this, "Payment Approved");
            startActivity(new Intent(this, CoffeeDispenseActivity.class));
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_payment;
    }
}
