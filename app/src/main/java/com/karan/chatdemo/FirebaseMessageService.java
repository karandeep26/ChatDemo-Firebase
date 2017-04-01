package com.karan.chatdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karan.chatdemo.model.ChatMessage;

/**
 * Created by stpl on 3/31/2017.
 */

public class FirebaseMessageService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DatabaseReference mFirebaseDataBaseRef = FirebaseDatabase.getInstance().getReference();
        mFirebaseDataBaseRef.child("messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                 if(!message.getMessageUser().equals(FirebaseAuth.getInstance()
                        .getCurrentUser().getDisplayName())){
                Log.d("NAME:",message.getMessageUser());
                message.setDelivered(true);
                mFirebaseDataBaseRef.child("messages").child(dataSnapshot.getKey()).setValue(message);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }
}
