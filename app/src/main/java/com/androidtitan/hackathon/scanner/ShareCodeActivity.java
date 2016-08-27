package com.androidtitan.hackathon.scanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.androidtitan.hackathon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareCodeActivity extends Activity {

    private static final String EXTRA_QRCODE_URL = "EXTRA_QRCODE_URL";

    @BindView(R.id.share_code_imageView) ImageView shareCodeImageView;

    public static Intent newIntent(Context context, int imageId) {
        Intent intent = new Intent(context, ShareCodeActivity.class);
        intent.putExtra(EXTRA_QRCODE_URL, imageId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sharecode);
        ButterKnife.bind(this);

        setTitle(getString(R.string.share_the_code));

        //getIntent().get

    }
}
