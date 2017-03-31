package com.karan.chatdemo;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by stpl on 3/30/2017.
 */

public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Intent service=new Intent(this,MyService.class);
        startService(service);

    }
}
