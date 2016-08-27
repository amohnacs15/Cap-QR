package com.androidtitan.hackathon.scanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.androidtitan.hackathon.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareCodeActivity extends Activity {

    private static final String EXTRA_QRCODE_URL = "EXTRA_QRCODE_URL";

    @BindView(R.id.share_code_imageView) ImageView shareCodeImageView;

    public static Intent newIntent(Context context, String imageId) {
        Intent intent = new Intent(context, ShareCodeActivity.class);
        intent.putExtra(EXTRA_QRCODE_URL, imageId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sharecode);
        ButterKnife.bind(this);

        //setTitle(getString(R.string.share_the_code));

        ImageView imageView = (ImageView) findViewById(R.id.share_code_imageView);
        try {
            Bitmap bitmap = encodeAsBitmap(getIntent().getExtras().getString(EXTRA_QRCODE_URL));
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, 400, 400, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 400, 0, 0, w, h);
        return bitmap;
    }
}
