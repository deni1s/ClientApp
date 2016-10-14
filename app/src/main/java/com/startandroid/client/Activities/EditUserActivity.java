package com.startandroid.client.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.startandroid.client.API.App;
import com.startandroid.client.R;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Денис on 09.08.2016.
 */
public class EditUserActivity extends FragmentActivity {
    EditText emailEditText, passwordEditText, nameEditText;
    ImageView avatarImageView;
    Button uploadImageButton, editButton;
    private static final int REQUEST = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_layout);

        avatarImageView = (ImageView) findViewById(R.id.avatarEdit);
        Picasso.with(getApplicationContext()).load(App.getInstance().getAppUser().getAvatar()).into(avatarImageView);

        uploadImageButton = (Button) findViewById(R.id.uploadImageEdit);
        editButton = (Button) findViewById(R.id.edit);
        emailEditText = (EditText) findViewById(R.id.emailEdit);

        passwordEditText = (EditText) findViewById(R.id.passwordEdit);
        nameEditText = (EditText) findViewById(R.id.nameEdit);

        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Отредактировано", Toast.LENGTH_LONG).show();
                finish();

            }
        });
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
            avatarImageView.setImageBitmap(img);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
