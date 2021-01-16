package com.example.nestledemo.ui.components.profile;

import android.content.Intent;
import android.os.Bundle;

import android.widget.EditText;

import android.widget.Toast;

import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.ui.components.home.HomeActivity;
import com.example.nestledemo.utils.AppUtils;
import com.example.nestledemo.utils.PrefUtils;


import butterknife.BindView;
import butterknife.OnClick;

import static com.example.nestledemo.utils.PrefUtils.FIRST_NAME;



public class ProfileActivity extends BaseActivity {

    @BindView(R.id.etFirstName)
    EditText etFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String firstName = PrefUtils.read(FIRST_NAME, "");
        if (!firstName.isEmpty()){
            etFirstName.setText(firstName);
        }

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile;
    }

    @OnClick(R.id.btnUpdate)
    void onUpdateClick(){

        String firstName = etFirstName.getText().toString();
        if (firstName.isEmpty()){
            AppUtils.showToast(this,"Please fill the first name");
            return;
        }
        PrefUtils.write(PrefUtils.FIRST_NAME, firstName);
        Toast.makeText(getApplicationContext(),"You have updated the profile!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeActivity.class));
    }

}