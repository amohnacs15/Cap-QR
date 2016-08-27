package com.androidtitan.hackathon;

import android.app.Application;

/**
 * Created by amohnacs on 8/25/16.
 */

public class App extends Application {

    public String user = "Shauvik";
    public static String payer = "sam";
    public static String payee = "adam";

    @Override
    public void onCreate() {
        super.onCreate();

//        new EndpointServerAsync().execute(new Pair<Context, String>(this, user));


        // Send money request
//        TransferToken token = new TransferToken();
//        token.setPayer(payer);
//        token.setAmount(100.00);
//        new InitiateTransferAsync().execute(new Pair<Context, TransferToken>(this, token));

        // Receive money
        //new CompleteTransferAsync(this).execute(new Pair<String, String>(payee, "e2Ftb3VudD0xMDAuMCwgcGF5ZXI9c2FtLCBzZWNyZXQ9czNjcjN0fQ=="));

    }
}
