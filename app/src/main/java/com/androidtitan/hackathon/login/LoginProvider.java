package com.androidtitan.hackathon.login;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by amohnacs on 8/25/16.
 */

public class LoginProvider implements LoginMVP.Provider {
    private static final String TAG = "LoginProvider";


    private static LoginProvider instance = null;
    private final FirebaseAuth firebaseAuthenticator;
    private Context context;

    String passedEmail;
    String passedPassword;

    public static LoginProvider getInstance(Context context) {
        if (instance == null) {
            instance = new LoginProvider(context);
        }
        return instance;
    }

    public LoginProvider(Context context) {

        this.context = context;
        firebaseAuthenticator = FirebaseAuth.getInstance();

    }

    @Override
    public void authenticateUser(final String email, final String password, final EventListener listener) {

        passedEmail = email;
        passedPassword = password;

        firebaseAuthenticator.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            listener.authenticateUserResult(true, email + " logged in!");

                        } else {
                            createUser(passedEmail, passedPassword, listener);
                        }
                    }
                });
    }

    private void createUser(String email, final String password, final EventListener listener) {
        firebaseAuthenticator.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            listener.authenticateUserResult(true, "User created");

                        } else {
                            listener.authenticateUserResult(false, task.getException().getMessage());
                        }
                    }
                });
    }


}
