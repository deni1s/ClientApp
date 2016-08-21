package com.startandroid.client.Fragments;

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

import com.startandroid.client.API.BaseApi;
import com.startandroid.client.R;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Денис on 29.07.2016.
 */
public class RegisterActivity extends FragmentActivity{

    EditText email, password, name;
    ImageView avatar;
    Button uploadImage, register;
    private static final int REQUEST = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        avatar = (ImageView) findViewById(R.id.avatar);
        uploadImage = (Button) findViewById(R.id.uploadImage);
        register = (Button) findViewById(R.id.register);
        email = (EditText) findViewById(R.id.emailRegister);
        password = (EditText) findViewById(R.id.passwordRegister);
        name = (EditText) findViewById(R.id.nameRegister);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmailValid(email.toString()) || isPasswordValid(password.toString())) {
                    Intent intent = new Intent(getApplicationContext(), MovieListActivity.class);
                    intent.putExtra("accessToken","" +getToken());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Неправльный email или пароль", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //void loads image into ImageView
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

    private String getToken(){
        Intent intent = new Intent(RegisterActivity.this, MovieListActivity.class);
        return new BaseApi().sendRequest(email.toString(), password.toString(), "EditUserInfo", intent);
    }

}

