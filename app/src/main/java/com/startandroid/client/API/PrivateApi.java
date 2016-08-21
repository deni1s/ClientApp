package com.startandroid.client.API;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.startandroid.client.BuildConfig;
import com.startandroid.client.Responses.TokenResponse;

/**
 * Created by Денис on 12.08.2016.
 */
class PrivateApi {

    TokenResponse responseObject;
    AsyncHttpClient client;
    String accessToken;

    public boolean acccessDenied(String accessToken){
        sendRequest(accessToken);
        //getAnswer
        if(true) { // accessDenied
            return true;
        }
        else return false;
    }

    public void sendRequest(String accesToken){
        client  = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("AccessToken", accesToken);
        client.post(BuildConfig.API_PATH, params,  new JsonHttpResponseHandler());
    }


}
