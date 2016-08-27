package com.androidtitan.hackathon.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.androidtitan.hackathon.R;
import com.androidtitan.hackathon.base.BaseActivity;
import com.androidtitan.hackathon.login.AccountActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/*(
TODO
Create Firebase table and generate its URL
Integrate firebase with a few simple calls to the table you created
 */

public class MainActivity extends BaseActivity implements MainMVP.View {
    private static final String TAG = "MainActivity";


    @BindView(R.id.signInButton)
    Button signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }

        final Intent intent = new Intent(this, AccountActivity.class);
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void displaySomething() {
        Log.d(TAG, "displaying something in view");

        Toast.makeText(this, "Event has made it back", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void sendUserToLoginActivity() {
        Log.e(TAG, "User logged out");
        startActivity(new Intent(this, AccountActivity.class));
    }

}
