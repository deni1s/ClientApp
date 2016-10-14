package com.startandroid.client.API;

import com.startandroid.client.API.AuthApi.AuthAPI;
import com.startandroid.client.API.MovieApi.MovieApi;

/**
 * Created by Денис on 11.10.2016.
 */
public class App {
    private static AuthAPI authApiInstance;
    private static AppUser appUserInstance;
    private static MovieApi movieApiInstance;
    private static App appInstance;

    public static App getInstance(){
        if (appInstance == null){
            appInstance = new App();
        }
        return appInstance;
    }


    public AppUser getAppUser() {
        return appUserInstance;
    }

    public AuthAPI getAuthApi() {
        return authApiInstance;
    }

    public MovieApi getMovieApi() {
        return movieApiInstance;
    }
}
