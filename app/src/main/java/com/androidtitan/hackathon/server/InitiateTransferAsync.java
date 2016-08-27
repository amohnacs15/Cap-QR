package com.androidtitan.hackathon.server;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.androidtitan.hackathon.myApi.MyApi;
import com.androidtitan.hackathon.myApi.model.TransferToken;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

import java.io.IOException;

/**
 * Created by amohnacs on 8/27/16.
 */

public class InitiateTransferAsync extends AsyncTask<Pair<Context, TransferToken>, Void, String> {

    MyApi capService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, TransferToken>... params) {

        if(capService == null) {

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {

                            request.setDisableGZipContent(true);
                        }
                    });

            capService = builder.build();
        }

        context = params[0].first;
        TransferToken tokenRequest = params[0].second;

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
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
