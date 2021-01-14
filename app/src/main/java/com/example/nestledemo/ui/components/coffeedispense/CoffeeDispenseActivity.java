package com.example.nestledemo.ui.components.coffeedispense;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.base.BaseActivity;
import com.example.nestledemo.ui.components.home.HomeActivity;
import com.example.nestledemo.utils.AppUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class CoffeeDispenseActivity extends BaseActivity {

    @BindView(R.id.tvProgress)
    TextView tvProgressStatus;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.progressBarCyclic)
    ProgressBar progressBarCyclic;

    @BindView(R.id.progressRL)
    RelativeLayout progressRL;

    private int status = 0;
    private Handler handler = new Handler();
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.showViews(progressBarCyclic);
    }

    @OnClick(R.id.btnStartDispense)
    void onStartDispense() {
        AppUtils.showViews(progressRL);
        startProgress();
    }

    private void startProgress() {
        new Thread(() -> {
            while (status < 100) {
                status += 1;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(() -> {
                    progressBar.setProgress(status);
                    String statusText = "Dispensing your coffee, please wait... " + status + "/100%";
                    tvProgressStatus.setText(statusText);
                    if (status == 100) {
                        AppUtils.hideViews(progressBarCyclic);
                        AppUtils.showToast(CoffeeDispenseActivity.this, "Your coffee is filled, please take your cup");
                        showAlert();
                    }
                });
            }
        }).start();
    }

    private void showAlert() {
        //Setting message manually and performing action on button click
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Success")
                .setMessage("Thank you for having Nestle Coffee!! See you again. ")
                .setCancelable(false)
//                .setPositiveButton("Thank you", (dialog, id) -> finish())
                .setNegativeButton("Have a Good one!", (dialog, id) -> {
                    dialog.cancel();
                    startActivity(new Intent(this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                });
        //Creating dialog box
        builder.create().show();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_coffee_dispense;
    }
}