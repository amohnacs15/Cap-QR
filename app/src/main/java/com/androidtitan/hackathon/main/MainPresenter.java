package com.androidtitan.hackathon.main;

import android.content.Context;
import android.util.Log;

import com.androidtitan.hackathon.base.BasePresenter;

/**
 * Created by amohnacs on 8/22/16.
 */

public class MainPresenter extends BasePresenter<MainMVP.View> implements MainMVP.Presenter, MainMVP.Provider.EventListener{
    private static final String TAG = "MainPresenter";

    private static MainPresenter instance = null;
    private Context context;

    private MainProvider provider;

    public static MainPresenter getInstance(Context context) {
        if(instance == null) {
            instance = new MainPresenter(context);
        }

        return instance;
    }

    public MainPresenter(Context context) {
        this.context = context;
        provider = MainProvider.getInstance(context);
    }

    @Override
    public void getSomething() {
        Log.d(TAG, "getting from Presenter");
        provider.fetchSomething(this);
    }

    @Override
    public void signOutUser() {
        provider.revokeAuthentication(this);
    }

    @Override
    public void onCallback() {
        Log.d(TAG, "calling back to Presenter");
        getMvpView().displaySomething();
    }

    @Override
    public void revokeAuthenticationResult(boolean result) {
        if(result) {
            getMvpView().sendUserToLoginActivity();
        }
    }
}
