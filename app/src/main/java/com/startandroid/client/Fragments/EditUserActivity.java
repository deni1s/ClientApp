package com.startandroid.client.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.startandroid.client.API.BaseApi;
import com.startandroid.client.API.UserApi;
import com.startandroid.client.R;
import com.startandroid.client.Responses.UserResponse;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Created by Денис on 09.08.2016.
 */
public class EditUserActivity extends FragmentActivity {
    EditText email, password, name;
    ImageView avatar;
    Button uploadImage, edit;
    private static final int REQUEST = 1;
    UserApi userApi = new UserApi();
    UserResponse user;
    String accessToken;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_layout);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            accessToken = extras.getString("accessToken");
        }

        EventBus.getDefault().registerSticky(this);


        Log.d("accessToken", accessToken);

        if(userApi.acccessDenied(accessToken)) {
            userApi.getUserInfo(getApplicationContext());
        } else {
            getFragmentManager().popBackStack();
        }


        avatar = (ImageView) findViewById(R.id.avatarEdit);
        Picasso.with(getApplicationContext()).load(user.getAvatar()).into(avatar);

        uploadImage = (Button) findViewById(R.id.uploadImageEdit);
        edit = (Button) findViewById(R.id.edit);
        email = (EditText) findViewById(R.id.emailEdit);

        password = (EditText) findViewById(R.id.passwordEdit);
        name = (EditText) findViewById(R.id.nameEdit);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Отредактировано", Toast.LENGTH_LONG).show();


            }
        });
    }


    private String getToken(){
        Intent intent = new Intent(EditUserActivity.this, MovieListActivity.class);
        return new BaseApi().sendRequest(email.toString(), password.toString(), "Enter", intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap img = null;

        if (requestCode == REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                img = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            avatar.setImageBitmap(img);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
