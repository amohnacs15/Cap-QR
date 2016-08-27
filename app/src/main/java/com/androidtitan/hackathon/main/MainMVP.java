package com.androidtitan.hackathon.main;

import com.androidtitan.hackathon.base.MvpPresenter;
import com.androidtitan.hackathon.base.MvpView;

import java.util.List;

/**
 * Created by amohnacs on 8/22/16.
 */

public interface MainMVP {

    interface Provider {
        //This interface is used if we are using a process other than an observable. We are creating our own callback.
        interface EventListener {
            void onCallback();

            void revokeAuthenticationResult(boolean result);

        }
        void fetchSomething(EventListener listener);

        void revokeAuthentication(EventListener listener);
    }

    interface Presenter {
        void getSomething();

        void signOutUser();

    }

    interface View extends MvpView{
        void displaySomething();

        void sendUserToLoginActivity();
    }

}
