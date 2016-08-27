package com.androidtitan.hackathon.main;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by amohnacs on 8/22/16.
 */

//TODO : Nessie Android SDK
    //https://github.com/nessieisreal/nessie-android-sdk

public class MainProvider implements MainMVP.Provider {
    private static final String TAG = "MainProvider";

    private static MainProvider instance = null;
    private Context context;

    public static MainProvider getInstance(Context context) {
        if(instance == null) {
            instance = new MainProvider(context);
        }
        return instance;
    }

    public MainProvider(Context context) {

        this.context = context;

    }

    @Override
    public void fetchSomething(EventListener listener) {
        //on completion callback
        Log.d(TAG, "fetching from Provider");
        listener.onCallback();
    }

    @Override
    public void revokeAuthentication(EventListener listener) {
        FirebaseAuth.getInstance().signOut();
        listener.revokeAuthenticationResult(true);
    }


}
