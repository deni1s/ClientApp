package com.startandroid.client.Model;

import com.startandroid.client.Responses.MovieResponse;

/**
 * Created by Денис on 11.08.2016.
 */
public class MovieClickedEvent {
    public MovieResponse.MoviesBean movie;

    public MovieClickedEvent(MovieResponse.MoviesBean movie) {
        this.movie = movie;
    }
}
