package com.startandroid.client.API.UserApi;

import com.startandroid.client.Responses.User;

/**
 * Created by Денис on 20.09.2016.
 */
public interface UserListener {
    // These methods are the different events and
    // need to pass relevant arguments related to the event triggered
    public void onDataLoaded(User user);
    // or when data has been loaded
    public void onFailure(String error);
}
