package com.startandroid.client.Model.API.BaseApi;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Денис on 20.09.2016.
 */
public interface RequestListener {
    void onDataLoaded(JSONArray data) throws JSONException;
    void onFailure(String error);
}