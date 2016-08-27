package com.androidtitan.hackathon.server;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.androidtitan.hackathon.myApi.MyApi;
import com.androidtitan.hackathon.myApi.model.TransferToken;
import com.androidtitan.hackathon.scanner.ShareCodeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

import java.io.IOException;

/**
 * Created by amohnacs on 8/27/16.
 */

public class InitiateTransferAsync extends AsyncTask<TransferToken, Void, String> {

    MyApi capService = null;
    private Context context;

    public InitiateTransferAsync(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(TransferToken... params) {

        if(capService == null) {

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://capqr-server.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {

                            request.setDisableGZipContent(true);
                        }
                    });

            capService = builder.build();
        }

        TransferToken tokenRequest = params[0];

        try {
            String secret = capService.initiateTransfer(tokenRequest.getPayer(), tokenRequest.getAmount()).execute().getSecret();
            tokenRequest.setSecret(secret);
            Log.d("Token Request", Base64.encodeBase64String(tokenRequest.toString().getBytes()));
            return Base64.encodeBase64String(tokenRequest.toString().getBytes());
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        //TODO: make QR code and show here
        context.startActivity(new Intent(ShareCodeActivity.newIntent(context, result)));
    }
}
