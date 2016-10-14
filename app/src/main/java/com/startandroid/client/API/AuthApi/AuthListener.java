package com.startandroid.client.API.AuthApi;

/**
 * Created by Денис on 20.09.2016.
 */
public interface AuthListener {
    void onDataLoaded(String accessToken);
    void onFailure(String error);
}