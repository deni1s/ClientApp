package com.startandroid.client.Model;

import android.content.Context;

import com.startandroid.client.Model.API.AuthApi.AuthAPI;
import com.startandroid.client.Model.API.MovieApi.MovieApi;
import com.startandroid.client.Model.API.UserApi.UserApi;

/**
 * Created by Денис on 11.10.2016.
 */
public class App {
    //TODO: remove Instance
    private AuthAPI authApi;
    private AppUser appUser;
    private MovieApi movieApi;
    private UserApi userApi;

    private static App appInstance;

    public static App getInstance() {
        return appInstance;
    }

    public static void createInstance(Context context) { //TODO: call at the application start
        appInstance = new App(context);
        //TODO: save context
    }

    private App(Context context){
        appUser = new AppUser(context);
        authApi = new AuthAPI();
        movieApi = new MovieApi();
        userApi = new UserApi();
    }

    //TODO: create in contructor all relations

    public AppUser getAppUser() {
        return appUser;
    }

    public UserApi getUserApi() {
        return userApi;
    }

    public AuthAPI getAuthApi() {
        return authApi;
    }

    public MovieApi getMovieApi() {
        return movieApi;
    }
}
