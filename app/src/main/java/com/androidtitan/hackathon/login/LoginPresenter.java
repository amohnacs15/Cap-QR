package com.androidtitan.hackathon.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.androidtitan.hackathon.base.BasePresenter;
import com.androidtitan.hackathon.main.MainMVP;
import com.androidtitan.hackathon.main.MainPresenter;
import com.androidtitan.hackathon.main.MainProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by amohnacs on 8/25/16.
 */

public class LoginPresenter extends BasePresenter<LoginMVP.View> implements LoginMVP.Presenter, LoginMVP.Provider.EventListener {
    private static final String TAG = "LoginPresenter";


    private static LoginPresenter instance = null;
    private Context context;

    private LoginProvider provider;

    private FirebaseAuth firebaseAuthenticator;
    private FirebaseAuth.AuthStateListener authStateListener;

    public static LoginPresenter getInstance(Context context) {
        if(instance == null) {
            instance = new LoginPresenter(context);
        }

        return instance;
    }

    public LoginPresenter(Context context) {
        this.context = context;
        provider = LoginProvider.getInstance(context);

        firebaseAuthenticator = FirebaseAuth.getInstance();

    }

    @Override
    public void attachView(LoginMVP.View mvpView) {
        super.attachView(mvpView);
        firebaseAuthenticator.addAuthStateListener(authStateListener);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (authStateListener != null) {
            firebaseAuthenticator.removeAuthStateListener(authStateListener);
        }
    }

    //implemented in the presenter to tie the authstatelistener to the presenter's scope and lifecycle
    public void checkUserState() {

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null) {
                    Log.d(TAG, "User is already logged in");

                } else {
                    Log.e(TAG, "User has not been logged in");

                }
            }
        };
    }

    @Override
    public void signInUser(String email, String password) {

        provider.authenticateUser(email, password, this);
    }

    @Override
    public void authenticateUserResult(boolean result, String resultMessage) {
        if(result) {
            getMvpView().sendUserToMainActivity(resultMessage);

        } else {
            //show some sort of error
        }
    }
}
