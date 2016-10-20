package com.startandroid.client.Model.API.UserApi;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.startandroid.client.BuildConfig;
import com.startandroid.client.Model.API.BaseApi.RequestListener;
import com.startandroid.client.Model.API.PrivateApi;
import com.startandroid.client.Model.Responses.User;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Денис on 12.08.2016.
 */
public class UserApi extends PrivateApi {
    public void getUserInfo(final UserListener listener) {
        RequestParams params = new RequestParams();
        params = prepareParams(params);
        sendRequest(BuildConfig.USER_INFO_PATH, params, new RequestListener() {
            @Override
            public void onDataLoaded(JSONArray data) throws JSONException {
                Gson gson = new Gson();
                User user = gson.fromJson(data.get(0).toString(), User.class);
                listener.onDataLoaded(user);
            }

            @Override
            public void onFailure(String error) {
                listener.onFailure(error);
            }
        });
    }
}
