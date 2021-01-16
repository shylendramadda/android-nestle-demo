package com.example.nestledemo.ui.components.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.ui.components.home.HomeActivity;
import com.example.nestledemo.utils.AppUtils;
import com.example.nestledemo.utils.PrefUtils;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.etMobile)
    EditText etMobile;

    @BindView(R.id.etOTP)
    EditText etOTP;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.btnValidate)
    Button btnValidate;

    @BindView(R.id.mobileLayout)
    TextInputLayout mobileLayout;

    @BindView(R.id.otpLayout)
    TextInputLayout otpLayout;

    private int otp;
    private String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btnLogin)
    void onLoginClick() {

        mobileNumber = etMobile.getText().toString();
        if (mobileNumber.isEmpty()) {
            AppUtils.showToast(this, "Please Enter mobile number");
        } else if (mobileNumber.length() != 13) {
            AppUtils.showToast(this, "Please Enter valid mobile number");

        } else {
            // After validation of mobile number
            otp = (int) (Math.random() * 9000) + 1000;
            // OTP sent
            AppUtils.showToast(this, "Your OTP is: " + otp);
            AppUtils.showToast(this, "Your OTP is: " + otp);
            AppUtils.hideViews(mobileLayout, btnLogin);
            AppUtils.showViews(otpLayout, btnValidate);
        }
    }

    @OnClick(R.id.btnValidate)
    void onValidateClick() {
        String enteredOTP = etOTP.getText().toString();
        if (enteredOTP.isEmpty()) {
            AppUtils.showToast(this, "Please enter OTP");
        } else if (Integer.parseInt(enteredOTP) == otp) {
            AppUtils.showToast(this, "Success");
            PrefUtils.write(PrefUtils.MOBILE_NUM, mobileNumber);
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            AppUtils.showToast(this, "Please enter valid OTP");
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }
}