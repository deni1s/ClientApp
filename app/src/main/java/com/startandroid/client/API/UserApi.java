package com.startandroid.client.API;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.startandroid.client.BuildConfig;
import com.startandroid.client.Fragments.EditUserActivity;
import com.startandroid.client.Responses.UserResponse;

import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;

/**
 * Created by Денис on 12.08.2016.
 */
public class UserApi extends PrivateApi  {

    AsyncHttpClient client;
    Gson gson;
    UserResponse user;

    @org.jetbrains.annotations.Contract(pure = true)
    public void getUserInfo(final Context context){
        client = new AsyncHttpClient();
        client.get(context,BuildConfig.API_PATH + BuildConfig.USER_INFO_PATH, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);
                gson = new Gson();
                user = gson.fromJson(responseString, UserResponse.class);
                Log.d("poster", user.getAvatar());
                EventBus.getDefault().postSticky(user);
                Intent intent = new Intent(context, EditUserActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                sendFinishMessage();
            }
        });
    }
}
