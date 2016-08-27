package com.androidtitan.hackathon.base.success;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidtitan.hackathon.R;
import com.androidtitan.hackathon.login.AccountActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ocarty on 8/27/16.
 */

public class SuccessActivity extends AppCompatActivity {

    private static final String EXTRA_TYPE = "EXTRA_TYPE";

    @BindView(R.id.textView)
    TextView successText;

    @BindView(R.id.doneButton)
    Button doneButton;

    String amount;

    public static Intent newIntent(Context context, boolean isReceiving) {
        Intent intent = new Intent(context, SuccessActivity.class);
        intent.putExtra(EXTRA_TYPE, isReceiving);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_success);
        ButterKnife.bind(this);

        if (getIntent().getExtras().getBoolean(EXTRA_TYPE)) {
            successText.setText(getString(R.string.congrats_you_apos_re_100_richer));
            amount = "$4100";
        } else {
            successText.setText(R.string.you_sent_money);
            amount = "$3900";
        }

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AccountActivity.newIntent(SuccessActivity.this, amount));
            }
        });
    }
}
