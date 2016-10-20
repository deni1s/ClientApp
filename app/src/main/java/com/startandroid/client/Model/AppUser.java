package com.startandroid.client.Model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Денис on 21.09.2016.
 */
public class AppUser {
    private static AppUser appUserInstance;
    public static final String STORAGE_NAME = "TokenStorage";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;

    AppUser(Context context) {
        createInstance(context);
    }

    //TODO: use simple constructor with context
    public static void createInstance(Context context) {
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }
	
	public boolean isAuthenticated() {
        try {
            return !getAccessToken().equals("");
        }
        catch(Exception e){
            return false;
        }
    }

    public void setEmail(String email) {
        editor.putString("Email", email);
        editor.apply();
    }

    public void setName(String name) {
        editor.putString("Name", name);
        editor.apply();
    }

    public void setAvatar(String avatar) {
        editor.putString("avatar", avatar);
        editor.apply();
    }

    public void setAccessToken(String accessToken) {
        editor.putString("accessToken", accessToken);
        editor.apply();
    }

    public void clearUser() {
        editor.clear();
        editor.apply();
    }

    public String getAccessToken() {
        return settings.getString("accessToken", null);
    }

    public String getEmail() {
        return settings.getString("Email", null);
    }


    public String getAvatar() {
        return settings.getString("avatar", null);
    }
}
