package com.karan.chatdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.FirebaseApp;
import com.karan.chatdemo.chatscreen.ChatActivity;
import com.karan.chatdemo.login.FirebaseLoginImpl;
import com.karan.chatdemo.login.FirebaseLoginPresenter;
import com.karan.chatdemo.login.FirebaseLoginView;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity implements FirebaseLoginView {
    FirebaseLoginPresenter loginPresenter;
    List<AuthUI.IdpConfig> providers;
    private int RC_SIGN_IN=1;
    FirebaseApp firebaseApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        firebaseApp=FirebaseApp.getInstance();
        providers=new ArrayList<>();
        providers.add(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());
        loginPresenter = new FirebaseLoginImpl(this);
        loginPresenter.checkLoginBeforeProceed();

    }

    @Override
    public void moveToLogin() {
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(false)
                .setProviders(providers).build(), RC_SIGN_IN);
    }

    @Override
    public void loggedIn(String user) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.parentPanel), "Signed in using " +
                user, Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
        Intent intent=new Intent(this, ChatActivity.class);
        startActivity(intent);
        finish();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                loggedIn(response != null ? response.getEmail() : null);

             } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button

                } else if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {

                } else if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {

                }
            }

        }
    }

}
