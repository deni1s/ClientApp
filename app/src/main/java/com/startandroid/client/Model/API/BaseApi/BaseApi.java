package com.startandroid.client.Model.API.BaseApi;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.startandroid.client.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Денис on 12.08.2016.
 */
public class BaseApi {

    AsyncHttpClient client  = new AsyncHttpClient();

    protected void sendRequest(String URL, RequestParams params, final RequestListener listener) {
        params = prepareParams(params);
        client.get(BuildConfig.API_PATH + URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONObject meta = null;
                String code = null;
                try {
                    meta = response.getJSONObject("meta");
                    code = meta.getString("code");
                } catch (JSONException e) {
                    listener.onFailure("Failure");
                    e.printStackTrace();
                }
                if (code.equals("1")) {
                    if (listener != null) {
                        JSONArray data = null;
                        try {
                            data = response.getJSONArray("data");
                            listener.onDataLoaded(data);
                        } catch (JSONException e) {
                               listener.onFailure("Unable to get data");
                               e.printStackTrace();
                            try {
                                listener.onFailure(meta.getString("error"));
                            } catch (JSONException e1) {
                                listener.onFailure("Ошибка ответа сервера");
                                e1.printStackTrace();
                            }
                        }
                    }
                } else {
                    try {
                        listener.onFailure(meta.getString("error"));
                    } catch (JSONException e) {
                        listener.onFailure("Ошибка ответа сервера");
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (listener != null) {
                    listener.onFailure("Unable to get data");
                }
            }
        });
    }

    protected RequestParams prepareParams(RequestParams params) {
        return params;
    }
}
