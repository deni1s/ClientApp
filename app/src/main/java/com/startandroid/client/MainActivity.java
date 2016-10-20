package com.startandroid.client;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.startandroid.client.Activities.MovieListActivity;
import com.startandroid.client.Activities.RegisterActivity;
import com.startandroid.client.Model.API.AuthApi.AuthAPI;
import com.startandroid.client.Model.API.AuthApi.AuthListener;
import com.startandroid.client.Model.API.UserApi.UserListener;
import com.startandroid.client.Model.App;
import com.startandroid.client.Model.Responses.User;

public class MainActivity extends AppCompatActivity {

    EditText emailView;
    EditText passwordView;
    CircularProgressView loadProgView;
    AlertDialog.Builder ad;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.createInstance(getApplicationContext());

        if (App.getInstance().getAppUser().isAuthenticated()) {
            authenticate();
            return;
        }
        setContentView(R.layout.activity_main);
        emailView = (EditText) findViewById(R.id.email);
        passwordView = (EditText) findViewById(R.id.password);

        loadProgView = (CircularProgressView) findViewById(R.id.progress);
        loadProgView.setVisibility(View.INVISIBLE);

        Button enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    attemptLogin();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }


    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void attemptLogin() throws InterruptedException {

        emailView.setError(null);
        passwordView.setError(null);
        final String email = emailView.getText().toString();
        final String password = passwordView.getText().toString();

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

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return;
        }
        loadProgView.setVisibility(View.VISIBLE);
        new AuthAPI().authenticate(email, password, new AuthListener() {
            @Override
            public void onDataLoaded(String accessToken) {
                App.getInstance().getAppUser().setAccessToken(accessToken);
                authenticate();
            }

            @Override
            public void onFailure(String error) {
                loadProgView.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(error)
                        .setMessage(error)
                        .setCancelable(false)
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void authenticate() {
            App.getInstance().getUserApi().getUserInfo(new UserListener() {
                @Override
                public void onDataLoaded(User user) {
                    App.getInstance().getAppUser().setAvatar(user.getAvatar());
                    App.getInstance().getAppUser().setEmail(user.getEmail());
                    App.getInstance().getAppUser().setName(user.getName());
                    Intent intent = new Intent(getApplicationContext(), MovieListActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(String error) {

                }
            });
    }
}
