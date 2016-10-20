package com.startandroid.client.Model.API.AuthApi;

import com.loopj.android.http.RequestParams;
import com.startandroid.client.BuildConfig;
import com.startandroid.client.Model.API.BaseApi.RequestListener;
import com.startandroid.client.Model.API.PrivateApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            public void onDataLoaded(JSONArray data) {
                String accessToken = null;
                try {
                    JSONObject obj = (JSONObject) data.get(0);
                    accessToken = obj.getString("accessToken");
                    listener.onDataLoaded(accessToken);
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFailure("Auth Problems");
                }
            }
            @Override
            public void onFailure(String error) {
                listener.onFailure(error);
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
            public void onDataLoaded(JSONArray data) throws JSONException {
                JSONObject obj = (JSONObject) data.get(0);
                String accessToken = obj.getString("accessToken");
                listener.onDataLoaded(accessToken);
            }

            @Override
            public void onFailure(String error) {
                listener.onFailure(error);
            }
        });
    }
}
