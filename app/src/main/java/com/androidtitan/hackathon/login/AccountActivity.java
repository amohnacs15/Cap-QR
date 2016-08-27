package com.androidtitan.hackathon.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.androidtitan.hackathon.R;
import com.androidtitan.hackathon.Utils;
import com.androidtitan.hackathon.base.BaseActivity;
import com.androidtitan.hackathon.main.MainActivity;
import com.androidtitan.hackathon.transaction.TransactionActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountActivity extends BaseActivity implements LoginMVP.View {
    private static final String TAG = "AccountActivity";

    public static final String LOGIN_RESULT_EXTRA = "loginactivity.loginresultextra";
    private static final int SLIDE_ANIM_DURATION = 300;
    private static final String EXTRA_AMOUNT = "EXTRA_AMOUNT";


    @BindView(R.id.welcomeMessage)
    TextView welcomeMessage;
    @BindView(R.id.accountStatus)
    TextView accountStatus;
    @BindView(R.id.accountBalance)
    TextView accountBalance;
    @BindView(R.id.send_button)
    TextView sendButton;
    @BindView(R.id.receive_button)
    TextView receiveButton;

    Handler handler;
    Animation fadeIn;

    public static Intent newIntent(Context context, String amount) {
        Intent intent = new Intent(context, AccountActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        if(getSupportActionBar() != null) {
            Log.e("Account activity", "get support action bar is null");
            getSupportActionBar().setElevation(0);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy h:mm a");
        String currentDateTime = dateFormat.format(new Date());
        accountStatus.setText("as of " + currentDateTime + " to send");

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.reveal_text);
        handler = new Handler();


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                slideEmOutLeft();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(TransactionActivity.newIntent(AccountActivity.this, 0));
                        startActivity(intent);
                    }
                }, 450);
            }
        });

        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                slideEmOutRight();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(TransactionActivity.newIntent(AccountActivity.this, 1));
                        startActivity(intent);
                    }
                }, 450);

            }
        });

        if(getIntent().hasExtra(EXTRA_AMOUNT)) {
            accountBalance.setText(getIntent().getExtras().getString(EXTRA_AMOUNT));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            public void run() {
                handler.postDelayed(new Runnable() {
                    public void run() {
                        accountBalance.setVisibility(View.VISIBLE);
                        accountBalance.startAnimation(fadeIn);
                        accountStatus.setVisibility(View.VISIBLE);
                        accountStatus.startAnimation(fadeIn);
                    }

                } , fadeIn.getDuration());
            }

        } , 500);
    }

    void slideEmOutLeft() {

        //accountBalance.setTranslationX(-500);
        accountBalance.animate()
                .translationX(-1000)
                .setInterpolator(new AccelerateInterpolator(3.f))
                .setDuration(SLIDE_ANIM_DURATION)
                .start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                accountStatus.animate()
                        .translationX(-1000)
                        .setInterpolator(new AccelerateInterpolator(3.f))
                        .setDuration(SLIDE_ANIM_DURATION)
                        .start();
            }
        }, 150);
    }

    void slideEmOutRight() {

        accountBalance.animate()
                .translationX(Utils.getScreenWidth(this) + 1000)
                .setInterpolator(new AccelerateInterpolator(3.f))
                .setDuration(SLIDE_ANIM_DURATION)
                .start();

        final Context context = this;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                accountStatus.animate()
                        .translationX(Utils.getScreenWidth(context) + 1000)
                        .setInterpolator(new AccelerateInterpolator(3.f))
                        .setDuration(SLIDE_ANIM_DURATION)
                        .start();
            }
        }, 150);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void sendUserToMainActivity(String resultMessage) {
        //todo : this should send the user to a different activity than 'MainActivity'
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(LOGIN_RESULT_EXTRA, resultMessage);

        startActivity(intent);
    }

}
