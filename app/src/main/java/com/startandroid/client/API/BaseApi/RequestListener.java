package com.startandroid.client.API.BaseApi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Денис on 20.09.2016.
 */
public interface RequestListener {
    void onDataLoaded(JSONObject data) throws JSONException;
    void onFailure(String error);
}