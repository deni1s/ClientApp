package com.startandroid.client.API.AuthApi;

import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.startandroid.client.API.BaseApi.RequestListener;
import com.startandroid.client.API.PrivateApi;
import com.startandroid.client.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Денис on 07.09.2016.
 */
public class AuthAPI extends PrivateApi {
    public void authenticate(String email, String password, final AuthListener listener) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);
        sendRequest(BuildConfig.API_AUTH_URL, params, new RequestListener() {
            @Override
            public void onDataLoaded(JSONObject data) {
                String accessToken = null;
                try {
                    accessToken = data.getString("accessToken");
                    listener.onDataLoaded(accessToken);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFailure("Auth Problems");
                }
            }
            @Override
            public void onFailure(String error) {

            }
        });
    }

    public void register(String email, String password, String name, final AuthListener listener){
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("password", password);
        params.put("Name", name);
        sendRequest(BuildConfig.API_REGISTRATION_URL, params, new RequestListener() {
            @Override
            public void onDataLoaded(JSONObject data) throws JSONException {
                String accessToken = data.getString("accessToken");
                listener.onDataLoaded(accessToken);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
}
