package com.startandroid.client.API;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ListView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.startandroid.client.BuildConfig;
import com.startandroid.client.Model.EndlessListView;
import com.startandroid.client.Model.MovieListAdapter;
import com.startandroid.client.Responses.MovieResponse;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Денис on 12.08.2016.
 */
public class MovieApi extends PrivateApi {

    AsyncHttpClient client;
    Gson gson;
    MovieResponse responseObject;
    List<MovieResponse.MoviesBean> movies;
    MovieListAdapter adapter;
    int moviesQuantity = 9;

    @org.jetbrains.annotations.Contract(pure = true)
    public void getMovieList(final Context context, final ListView listview, final FragmentManager fragmentManager, final CircularProgressView progressView){
        client = new AsyncHttpClient();
        client.get(context, BuildConfig.API_PATH + BuildConfig.MOVIE_LIST_PATH, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressView.startAnimation();
                String responseString = new String(responseBody);
                gson = new Gson();
                responseObject = gson.fromJson(responseString, MovieResponse.class);
                movies = responseObject.getMovies();
                adapter = new MovieListAdapter(context, movies, fragmentManager);
                listview.setAdapter(adapter);
                progressView.stopAnimation();
                progressView.setVisibility(View.GONE);
                listview.setOnScrollListener(new EndlessListView(context, moviesQuantity) {
                    @Override
                    public void loadMore(int page, int totalItemsCount) {
                            movies.addAll(responseObject.getMovies());
                            adapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                sendFinishMessage();
            }
        });
    }
}
