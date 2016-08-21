package com.startandroid.client.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.startandroid.client.API.MovieApi;
import com.startandroid.client.MainActivity;
import com.startandroid.client.Model.MovieListAdapter;
import com.startandroid.client.R;
import com.startandroid.client.Responses.MovieResponse;

import java.util.List;

/**
 * Created by Денис on 29.07.2016.
 */
public class MovieListActivity extends ActionBarActivity {

    ListView listView;
    MovieListAdapter adapter;
    final MovieApi movieApi = new MovieApi();
    List<MovieResponse.MoviesBean> moviesBeen;
    String accessToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            accessToken = extras.getString("accessToken");
        }

        listView = (ListView) findViewById(R.id.list);
        final CircularProgressView progressView = (CircularProgressView) findViewById(R.id.progress_view);
        if(movieApi.acccessDenied(accessToken)) {
            movieApi.getMovieList(getApplicationContext(), listView, getSupportFragmentManager(), progressView);
            setDrawer();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private void setDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.drawer_item_settings),
                        new SecondaryDrawerItem().withName(R.string.action_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.quit)
                ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                if (drawerItem instanceof SecondaryDrawerItem) {
                    String menuItem = MovieListActivity.this.getString(((SecondaryDrawerItem) drawerItem).getNameRes());
                    switch (menuItem){
                        case "Редактировать профиль" :
                            Intent intent = new Intent(MovieListActivity.this, EditUserActivity.class);
                            intent.putExtra("accessToken","" + accessToken);
                            startActivity(intent);
                            break;
                        case "Выйти":
                            startActivity(new Intent(MovieListActivity.this, MainActivity.class));
                            break;
                    }
                }}
        })
                .build();
    }
}