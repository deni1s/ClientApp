package com.startandroid.client.API.MovieApi;

import com.loopj.android.http.RequestParams;
import com.startandroid.client.API.BaseApi.RequestListener;
import com.startandroid.client.API.PrivateApi;
import com.startandroid.client.BuildConfig;
import com.startandroid.client.Responses.MovieResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Денис on 12.08.2016.
 */
public class MovieApi extends PrivateApi {
    public void getMovieList(final MovieListener listener){
        RequestParams params = new RequestParams();
        params = prepareParams(params);
        sendRequest(BuildConfig.MOVIE_LIST_PATH, params, new RequestListener() {
            @Override
            public void onDataLoaded(JSONObject data) {
                try {
                    listener.onDataLoaded((ArrayList<MovieResponse.MoviesBean>) data.get("movieList"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFailure("error parsing");
                }
            }

            @Override
            public void onFailure(String error) {
                listener.onFailure(error);
            }
        });
    }
}
