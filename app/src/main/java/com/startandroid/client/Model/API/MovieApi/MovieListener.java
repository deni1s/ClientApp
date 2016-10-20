package com.startandroid.client.Model.API.MovieApi;

import com.startandroid.client.Model.Responses.MovieResponse;

import java.util.List;

/**
 * Created by Денис on 20.09.2016.
*/
public interface MovieListener{
    public void onDataLoaded(List<MovieResponse> movies);
    public void onFailure(String error);
}
