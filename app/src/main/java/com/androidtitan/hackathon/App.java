package com.androidtitan.hackathon;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.Pair;


import com.androidtitan.hackathon.myApi.model.TransferToken;
import com.androidtitan.hackathon.server.EndpointServerAsync;
import com.androidtitan.hackathon.server.InitiateTransferAsync;

/**
 * Created by amohnacs on 8/25/16.
 */

public class App extends Application {

    public String user = "Shauvik";
    public String payer = "sam";
    public String payee = "adam";

    @Override
    public void onCreate() {
        super.onCreate();

//        new EndpointServerAsync().execute(new Pair<Context, String>(this, user));


        // Send money request
        TransferToken token = new TransferToken();
        token.setPayer(payer);
        token.setAmount(100.00);
        new InitiateTransferAsync().execute(new Pair<Context, TransferToken>(this, token));



    }
}
