package com.startandroid.client.Model.Events;

import com.startandroid.client.Model.Responses.MovieResponse;

/**
 * Created by Денис on 11.08.2016.
 */
public class MovieClickedEvent {
    public MovieResponse movie;

    public MovieClickedEvent(MovieResponse movie) {
        this.movie = movie;
    }
}
