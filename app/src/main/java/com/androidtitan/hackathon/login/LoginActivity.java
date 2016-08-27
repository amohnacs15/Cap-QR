package com.androidtitan.hackathon.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.androidtitan.hackathon.R;
import com.androidtitan.hackathon.base.BaseActivity;
import com.androidtitan.hackathon.scanner.ScannerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class LoginActivity extends BaseActivity implements LoginMVP.View {
    private static final String TAG = "LoginActivity";

    public static final String LOGIN_RESULT_EXTRA = "loginactivity.loginresultextra";

    private LoginPresenter presenter;

    @BindView(R.id.login_input_email)
    EditText emailEditText;

    @BindView(R.id.login_input_password)
    EditText passwordEditText;

    @BindView(R.id.login_TextView)
    TextView goTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = LoginPresenter.getInstance(this);
        presenter.attachView(this);


        goTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Missing email or password", LENGTH_LONG).show();
                }
                //else if : some other condition
                else {
                    presenter.signInUser(emailEditText.getText().toString(), passwordEditText.getText().toString());
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void sendUserToMainActivity(String resultMessage) {
        //todo : this should send the user to a different activity than 'MainActivity'
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra(LOGIN_RESULT_EXTRA, resultMessage);
//
//        startActivity(intent);

        //Remove this upon merge
        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }
}
