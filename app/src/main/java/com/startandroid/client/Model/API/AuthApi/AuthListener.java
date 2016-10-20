package com.startandroid.client.Model.API.AuthApi;

/**
 * Created by Денис on 20.09.2016.
 */
public interface AuthListener {
    void onDataLoaded(String accessToken);
    void onFailure(String error);
}