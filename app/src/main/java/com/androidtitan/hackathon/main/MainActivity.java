package com.androidtitan.hackathon.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtitan.hackathon.R;
import com.androidtitan.hackathon.base.BaseActivity;
import com.androidtitan.hackathon.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/*(
TODO
Create Firebase table and generate its URL
Integrate firebase with a few simple calls to the table you created
 */

public class MainActivity extends BaseActivity implements MainMVP.View {
    private static final String TAG = "MainActivity";

    private MainPresenter presenter;

    @BindView(R.id.result_TextView)
    TextView resultTextView;
    @BindView(R.id.signout_TextView)
    TextView signOutTextView;

    private String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = MainPresenter.getInstance(this);
        presenter.attachView(this);

        signOutTextView.setVisibility(View.INVISIBLE);

        if(getIntent() != null) {
            resultString = getIntent().getStringExtra(LoginActivity.LOGIN_RESULT_EXTRA);
            resultTextView.setText(resultString);
            signOutTextView.setVisibility(View.VISIBLE);
        }

        signOutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signOutUser();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Event Sent", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                Log.d(TAG, "sending event from View");
                presenter.getSomething();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void displaySomething() {
        Log.d(TAG, "displaying something in view");

        Toast.makeText(this, "Event has made it back", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void sendUserToLoginActivity() {
        Log.e(TAG, "User logged out");
        startActivity(new Intent(this, LoginActivity.class));
    }
}
