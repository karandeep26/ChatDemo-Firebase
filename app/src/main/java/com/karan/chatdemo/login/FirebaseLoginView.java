package com.karan.chatdemo.login;

/**
 * Created by stpl on 3/30/2017.
 */

public interface FirebaseLoginView {
    void moveToLogin();

    void loggedIn(String user);
}
