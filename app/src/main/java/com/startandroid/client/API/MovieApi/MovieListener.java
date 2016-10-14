package com.startandroid.client.API.MovieApi;

import com.startandroid.client.Responses.MovieResponse;

import java.util.ArrayList;

/**
 * Created by Денис on 20.09.2016.
*/
public interface MovieListener{
    public void onDataLoaded(ArrayList<MovieResponse.MoviesBean> movies);
    public void onFailure(String error);
}
