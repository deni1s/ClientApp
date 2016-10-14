package com.startandroid.client.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.startandroid.client.API.App;
import com.startandroid.client.MainActivity;
import com.startandroid.client.R;

/**
 * Created by Денис on 14.10.2016.
 */
public class NavigationBar extends ActionBarActivity {

    public void setDrawer(final Context activityContext, int toolbarId) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
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
                    String menuItem = activityContext.getString(((SecondaryDrawerItem) drawerItem).getNameRes());
                    switch (menuItem) {
                        case "Редактировать профиль":
                            startActivity(new Intent(activityContext, EditUserActivity.class));
                            break;
                        case "Выйти":
                            App.getInstance().getAppUser().clearUser();
                            startActivity(new Intent(activityContext, MainActivity.class));
                            break;
                    }
                }
            }
        })
                .build();
    }
}
