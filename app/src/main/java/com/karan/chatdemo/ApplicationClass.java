package com.karan.chatdemo;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by stpl on 3/30/2017.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
