package com.example.nestledemo.ui.components.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nestledemo.ui.components.flavors.FlavorsActivity;
import com.example.nestledemo.utils.AppUtils;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final String TAG = "SimpleScannerActivity";
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera(); // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        String rawResultText = rawResult.getText();
        String format = rawResult.getBarcodeFormat().toString();
        Log.v(TAG, rawResultText); // Prints scan results
        Log.v(TAG, format); // Prints the scan format (qrcode, pdf417 etc.)
        AppUtils.showToast(this, "Result: " + rawResultText + " Format: " + format);
        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
        startActivity(new Intent(this, FlavorsActivity.class));
        finish();
    }
}