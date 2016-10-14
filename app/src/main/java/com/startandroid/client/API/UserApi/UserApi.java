package com.startandroid.client.API.UserApi;

import com.loopj.android.http.RequestParams;
import com.startandroid.client.API.BaseApi.RequestListener;
import com.startandroid.client.API.PrivateApi;
import com.startandroid.client.BuildConfig;
import com.startandroid.client.Responses.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Денис on 12.08.2016.
 */
public class UserApi extends PrivateApi {
    public void getUserInfo(final UserListener listener) {
        RequestParams params = new RequestParams();
        params = prepareParams(params);
        sendRequest(BuildConfig.USER_INFO_PATH, params, new RequestListener() {
            @Override
            public void onDataLoaded(JSONObject data) throws JSONException {
                listener.onDataLoaded((User) data.get("userInfo"));
            }

            @Override
            public void onFailure(String error) {
                listener.onFailure(error);
            }
        });
    }
}
