package com.startandroid.client.API;

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
    private static Context context = null;

    public static void createInstance(Context contextt) {
        context = contextt;
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }
	
	public boolean isAuthenticated() {
        if (getAccessToken().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public void addEmail(String email) {
        editor.putString("Email", email);
        editor.apply();
    }

    public void addName(String name) {
        editor.putString("Name", name);
        editor.apply();
    }

    public void addAvatar(String avatar) {
        editor.putString("avatar", avatar);
        editor.apply();
    }

    public void addAccessToken(String accessToken) {
        editor.putString("accessToken", accessToken);
        editor.apply();
    }

    public static void clearUser() {
        editor.clear();
        editor.apply();
    }

    public String getAccessToken() {
        return settings.getString("accessToken", null );
    }

    public String getEmail() {
        return settings.getString("Email", null );
    }


    public String getAvatar() {
        return settings.getString("avatar", null);
    }
}
