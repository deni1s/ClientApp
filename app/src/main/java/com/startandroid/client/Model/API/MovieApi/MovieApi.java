package com.startandroid.client.Model.API.MovieApi;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.startandroid.client.BuildConfig;
import com.startandroid.client.Model.API.BaseApi.RequestListener;
import com.startandroid.client.Model.API.PrivateApi;
import com.startandroid.client.Model.Responses.MovieResponse;

import org.json.JSONArray;
import org.json.JSONException;

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
            public void onDataLoaded(JSONArray data) throws JSONException {
                ArrayList<MovieResponse> movies = new ArrayList<MovieResponse>();
                MovieResponse movie = null;
                for (int i=0; i < data.length(); i++) {
                    Gson gson = new Gson();
                    movie = gson.fromJson(data.get(i).toString(), MovieResponse.class);
                    movies.add(movie);
                }
                listener.onDataLoaded(movies);
            }
            @Override
            public void onFailure(String error) {
                listener.onFailure(error);
            }
        });
    }
}
