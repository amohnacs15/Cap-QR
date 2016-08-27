package com.androidtitan.hackathon.scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.androidtitan.hackathon.R;
import com.androidtitan.hackathon.base.BaseActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScannerActivity extends BaseActivity  {
    private static final String TAG = "ScannerActivity";

    @BindView(R.id.scan_button)
    Button scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scanner);
        ButterKnife.bind(this);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(ScannerActivity.this).initiateScan();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    // Parsing bar code reader result
                    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle("Scan Result");
                            builder.setMessage(result.getContents());
                            AlertDialog alert1 = builder.create();
                            alert1.show();
                }
                break;
        }
    }
}
