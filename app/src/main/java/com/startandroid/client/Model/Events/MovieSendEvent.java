package com.startandroid.client.Model.Events;

import com.startandroid.client.Model.Responses.MovieResponse;

/**
 * Created by Денис on 19.08.2016.
 */
public class MovieSendEvent {
    public MovieResponse movie;

    public MovieSendEvent(MovieResponse movie) {
        this.movie = movie;
    }
}
