package com.karan.chatdemo.chatscreen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karan.chatdemo.R;
import com.karan.chatdemo.model.ChatMessage;

public class ChatActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    AppCompatEditText textInputLayout;
    RecyclerViewAdapter chatAdapter;
    boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        fab = $(R.id.fab);
        recyclerView = $(R.id.list_of_messages);
        textInputLayout = $(R.id.input);
        DatabaseReference mFirebaseDataBaseRef = FirebaseDatabase.getInstance().getReference();
        mFirebaseDataBaseRef.keepSynced(true);
        chatAdapter = new RecyclerViewAdapter(ChatMessage.class, R.layout.chat_message,
                mFirebaseDataBaseRef.child("messages"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);
        fab.setOnClickListener(v -> {
            ChatMessage chatMessage = new ChatMessage(textInputLayout.getText().toString(),
                    FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), isConnected,false );
            mFirebaseDataBaseRef.child("messages").push().setValue(chatMessage);
            textInputLayout.setText("");
        });
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                isConnected=connected;
                if (connected) {
                    System.out.println("connected");
                } else {
                    System.out.println("not connected");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Listener was cancelled");
            }
        });

    }

    @SuppressWarnings("unchecked")
    private <T extends View> T $(int id) {
        return (T) findViewById(id);
    }


}

