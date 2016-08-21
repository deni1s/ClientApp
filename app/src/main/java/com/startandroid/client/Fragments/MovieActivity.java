package com.startandroid.client.Fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.startandroid.client.Model.MovieSendEvent;
import com.startandroid.client.R;
import com.startandroid.client.Responses.MovieResponse;

import de.greenrobot.event.EventBus;

/**
 * Created by Денис on 08.08.2016.
 */
public class MovieActivity extends FragmentActivity {

    MovieResponse.MoviesBean movie;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onclicked_item);

        EventBus.getDefault().registerSticky(this);

        ImageView progilePoster = (ImageView) findViewById(R.id.profilePoster);
        ImageView detailedPoster = (ImageView) findViewById(R.id.detailedPoster);
        ImageView originalPoster = (ImageView) findViewById(R.id.originalPoster);


        Picasso.with(getApplicationContext()).load(movie.getPosters().getProfile()).into(progilePoster);
        Picasso.with(getApplicationContext()).load(movie.getPosters().getDetailed()).into(detailedPoster);
        Picasso.with(getApplicationContext()).load(movie.getPosters().getOriginal()).into(originalPoster);


        TextView synopsis = (TextView) findViewById(R.id.synopsis);
        synopsis.setText(movie.getSynopsis());

        TextView release_data = (TextView) findViewById(R.id.release);
        release_data.setText(movie.getReleaseDates().getTheater());


    }

    public void onEventMainThread(MovieSendEvent event){
       this.movie = event.movie;
    }
}
