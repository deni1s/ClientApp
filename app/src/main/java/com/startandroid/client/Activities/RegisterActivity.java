package com.startandroid.client.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.startandroid.client.Model.API.AuthApi.AuthListener;
import com.startandroid.client.Model.App;
import com.startandroid.client.R;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Денис on 29.07.2016.
 */
public class RegisterActivity extends FragmentActivity{

    EditText emailView, passwordView, nameView;
    ImageView avatarView;
    Button uploadImageButton, registerButton;
    CircularProgressView loadProgView;
    private static final int REQUEST = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        loadProgView = (CircularProgressView) findViewById(R.id.progress);
        loadProgView.setVisibility(View.INVISIBLE);

        avatarView = (ImageView) findViewById(R.id.avatar);
        uploadImageButton = (Button) findViewById(R.id.uploadImage);
        registerButton = (Button) findViewById(R.id.register);
        emailView = (EditText) findViewById(R.id.emailRegister);
        passwordView = (EditText) findViewById(R.id.passwordRegister);
        nameView = (EditText) findViewById(R.id.nameRegister);

        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    attemptRegister();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void attemptRegister() throws InterruptedException {
        emailView.setError(null);
        passwordView.setError(null);
        nameView.setError(null);
        final String email = emailView.getText().toString();
        final String password = passwordView.getText().toString();
        final String name = nameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            nameView.setError(getString(R.string.error_field_required));
            focusView = nameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return;
        }
        loadProgView.setVisibility(View.VISIBLE);
        App.getInstance().getAuthApi().register(email, password, name, new AuthListener() {
            @Override
            public void onDataLoaded(String accessToken) {
                App.getInstance().getAppUser().setAccessToken(accessToken);
                enter();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void enter() {
        Intent intent = new Intent(getApplicationContext(), MovieListActivity.class);
        startActivity(intent);
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
            avatarView.setImageBitmap(img);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

