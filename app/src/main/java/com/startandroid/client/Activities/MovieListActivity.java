package com.startandroid.client.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.startandroid.client.Model.API.MovieApi.MovieApi;
import com.startandroid.client.Model.API.MovieApi.MovieListener;
import com.startandroid.client.Model.EndlessListView;
import com.startandroid.client.Model.Responses.MovieResponse;
import com.startandroid.client.R;
import com.startandroid.client.View.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 29.07.2016.
 */
public class MovieListActivity extends BaseActivity {

    ListView listView;
    MovieListAdapter adapter;
    final MovieApi movieApi = new MovieApi();
    ArrayList<MovieResponse> moviesBean = new ArrayList<MovieResponse>();
    CircularProgressView progressView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        listView = (ListView) findViewById(R.id.list);
        progressView = (CircularProgressView) findViewById(R.id.progress_view);

        attemptSetAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }


    private void attemptSetAdapter() {
        movieApi.getMovieList(new MovieListener() {
            @Override
            public void onDataLoaded(final List<MovieResponse> movies) {
                moviesBean.addAll(movies);
                setAdapter(moviesBean);
                listView.setOnScrollListener(new EndlessListView(getApplicationContext(), movies.size()) {
                    @Override
                    public void loadMore(int page, int totalItemsCount) {
                        loadMoreMovies();
                    }
                });
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
            }
        });
    }

    private void setAdapter(ArrayList<MovieResponse> movies) {
        adapter = new MovieListAdapter(getApplicationContext(), movies, getSupportFragmentManager());
        listView.setAdapter(adapter);
        progressView.setVisibility(View.INVISIBLE);
    }

    private void loadMoreMovies() {
        progressView.setVisibility(View.VISIBLE);
        movieApi.getMovieList(new MovieListener() {
            @Override
            public void onDataLoaded(final List<MovieResponse> movies) {
                moviesBean.addAll(movies);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
            }
        });
        progressView.setVisibility(View.INVISIBLE);
    }
}