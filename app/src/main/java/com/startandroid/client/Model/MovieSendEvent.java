package com.startandroid.client.Model;

import com.startandroid.client.Responses.MovieResponse;

/**
 * Created by Денис on 19.08.2016.
 */
public class MovieSendEvent {
    public MovieResponse.MoviesBean movie;

    public MovieSendEvent(MovieResponse.MoviesBean movie) {
        this.movie = movie;
    }
}
