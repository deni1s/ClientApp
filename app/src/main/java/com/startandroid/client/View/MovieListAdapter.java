package com.startandroid.client.View;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.startandroid.client.Activities.MovieActivity;
import com.startandroid.client.Model.Events.MovieSendEvent;
import com.startandroid.client.Model.Responses.MovieResponse;
import com.startandroid.client.R;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Денис on 31.07.2016.
 */
public class MovieListAdapter extends BaseAdapter{

    private List<MovieResponse> item;
    private Context context;
    FragmentManager fragmentManager;

    public MovieListAdapter(Context context, List<MovieResponse> item, FragmentManager fragmentManager) {
        this.item = item;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public MovieResponse getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.each_list_item, parent, false);

        MovieResponse item = (MovieResponse) getItem(position);
        ImageView image = (ImageView) rowView.findViewById(R.id.thumbnail);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView rating = (TextView) rowView.findViewById(R.id.rating);
        String imageUrl = item.getPosters().getOriginal();
        Picasso.with(context).load(imageUrl).into(image);
        title.setText(item.getTitle());
        rating.setText(item.getRatings().getAudience_rating());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new MovieSendEvent(getItem(position)));
                Intent intent = new Intent(context, MovieActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
