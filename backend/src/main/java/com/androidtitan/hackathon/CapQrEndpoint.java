/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.androidtitan.hackathon;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.repackaged.com.google.gson.JsonElement;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.appengine.repackaged.com.google.gson.JsonParser;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "hackathon.androidtitan.com",
    ownerName = "hackathon.androidtitan.com",
    packagePath=""
  )
)
public class CapQrEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public Message sayHi(@Named("name") String name) {
        Message response = new Message();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "initiateTransfer")
    public TransferToken initiateTransfer(@Named("payer") String payer, @Named("amount") double amount) {
        TransferToken token = new TransferToken();
        token.setPayer(payer);
        token.setAmount(amount);
        token.setSecret("s3cr3t");

        return token;
    }

    @ApiMethod(name = "completeTransfer")
    public Message completeTransfer(@Named("payee") String payee, @Named("token") String token) {

        TransferToken parsedToken = new TransferToken();
        Message m = new Message();
        m.setData("FAIL");

        JsonElement telem = new JsonParser().parse(token);
        if(telem != null) {
            JsonObject tobj = telem.getAsJsonObject();
            parsedToken.setPayer(tobj.get("payer").getAsString());
            parsedToken.setAmount(tobj.get("amount").getAsDouble());
            parsedToken.setSecret(tobj.get("secret").getAsString());

            if("s3cr3t".equals(parsedToken.getSecret())) {
                m.setData("SUCCESS");
            }
        }

        return m;
    }

}
