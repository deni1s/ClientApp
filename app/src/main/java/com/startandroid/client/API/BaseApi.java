package com.startandroid.client.API;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.startandroid.client.BuildConfig;
import com.startandroid.client.Responses.TokenResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by Денис on 12.08.2016.
 */
public class BaseApi {
    Context context;
    protected String accessToken;
    private static AsyncHttpClient client;
    TokenResponse responseObject;


    public String sendRequest(String email, String password, String status, Intent intent){
        client  = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put(email, password);
        params.put("", status);
        client.post(BuildConfig.API_PATH, params,  new JsonHttpResponseHandler());
        return issueToken(context, intent);
    }


    //only to know how to send, no usage
    private void sendJson(Boolean isRegistrated, String password, Context context) throws JSONException, UnsupportedEncodingException {
        JSONObject jsonParams = new JSONObject();
      //  jsonParams.put("email", user.getEmail());
        jsonParams.put("password", password);

        jsonParams.put("isRegistrated", isRegistrated);

        StringEntity entity = new StringEntity(jsonParams.toString());
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        client.post(context, "URL", entity, "application/json", new JsonHttpResponseHandler());
        /// wait Response
    }

    @org.jetbrains.annotations.Contract(pure = true)
    private String issueToken(final Context context, final Intent intent) {
        client.get(context,BuildConfig.API_PATH + BuildConfig.TOKEN_PATH, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);
                Gson gson = new Gson();
                responseObject = gson.fromJson(responseString, TokenResponse.class);
                accessToken = responseObject.getData().getAccessToken();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        } );
        return accessToken;
    }
}
