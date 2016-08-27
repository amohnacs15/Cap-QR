package com.androidtitan.hackathon.scanner;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.androidtitan.hackathon.base.BaseActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends BaseActivity implements ZXingScannerView.ResultHandler {
    private static final String TAG = "ScannerActivity";

    private ZXingScannerView scannerView;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Scan Result");
                builder.setMessage(result.getText());
                AlertDialog alert1 = builder.create();
                alert1.show();
    }
}
