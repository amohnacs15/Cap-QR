package com.androidtitan.hackathon.server;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.androidtitan.hackathon.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by amohnacs on 8/27/16.
 */

public class CompleteTransferAsync extends AsyncTask<Pair<String, String>, Void, String> {

    MyApi capService = null;
    private Context context;

    public CompleteTransferAsync(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(Pair<String, String>... params) {

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

        Pair<String, String> details = params[0];

        String payee = details.first;
        String token = details.second;

        try {
            return capService.completeTransfer(payee, token).execute().getData();
        } catch (IOException e) {
            Log.e("Scan Error", e.getMessage());
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
