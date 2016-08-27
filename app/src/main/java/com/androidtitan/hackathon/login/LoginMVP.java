package com.androidtitan.hackathon.login;

import com.androidtitan.hackathon.base.MvpView;

/**
 * Created by amohnacs on 8/25/16.
 */

public interface LoginMVP {

    interface Provider {

        interface EventListener {
            void authenticateUserResult(boolean result, String resultMessage);

        }

        void authenticateUser(String email, String password, EventListener listener);
    }

    interface Presenter {

        void signInUser(String email, String password);
    }

    interface View extends MvpView {

        void sendUserToMainActivity(String resultMessage);
    }

}
