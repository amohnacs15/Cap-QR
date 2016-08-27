package com.androidtitan.hackathon.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidtitan.hackathon.R;
import com.androidtitan.hackathon.base.BaseActivity;
import com.androidtitan.hackathon.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountActivity extends BaseActivity implements LoginMVP.View {
    private static final String TAG = "AccountActivity";

    public static final String LOGIN_RESULT_EXTRA = "loginactivity.loginresultextra";


    @BindView(R.id.accountStatus)
    TextView accountStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy h:mm a");
        String currentDateTime = dateFormat.format(new Date());
        accountStatus.setText("as of " + currentDateTime +  " to send");



        accountStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
