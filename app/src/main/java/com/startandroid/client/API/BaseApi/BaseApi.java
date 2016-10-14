package com.startandroid.client.API.BaseApi;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.startandroid.client.BuildConfig;

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
                    code = (String) meta.get("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (code.equals("0")) {
                    if (listener != null) {
                        JSONObject data = null;
                        try {
                            data = response.getJSONObject("data");
                            listener.onDataLoaded(data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            try {
                                listener.onFailure((String) meta.get("ErrorDescription"));
                            } catch (JSONException e1) {
                                listener.onFailure("Ошибка ответа сервера");
                                e1.printStackTrace();
                            }
                        }
                    }
                } else {
                    try {
                        listener.onFailure((String) meta.get("ErrorDescription"));
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
