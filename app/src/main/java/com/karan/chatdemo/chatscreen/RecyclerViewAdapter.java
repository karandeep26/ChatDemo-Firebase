package com.karan.chatdemo.chatscreen;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;
import com.karan.chatdemo.R;
import com.karan.chatdemo.model.ChatMessage;

/**
 * Created by stpl on 3/30/2017.
 */

public class RecyclerViewAdapter extends FirebaseRecyclerAdapter<ChatMessage, RecyclerViewAdapter
        .ChatMessageViewHolder> {

    /**
     * @param modelClass  Firebase will marshall the data at a location into
     *                    an instance of a class that you provide
     * @param modelLayout This is the layout used to represent a single item in the list.
     *                    You will be responsible for populating an instance of the
     *                    corresponding
     *                    view with the data from an instance of modelClass.
     * @param ref         The Firebase location to watch for data changes. Can also be a
     *                    slice of a location,
     *                    using some combination of {@code limit()}, {@code startAt()}, and
     *                    {@code endAt()}.
     */
    RecyclerViewAdapter(Class<ChatMessage> modelClass, int modelLayout, Query ref) {
        super(modelClass, modelLayout, ChatMessageViewHolder.class, ref);

    }

    @Override
    protected void populateViewHolder(ChatMessageViewHolder viewHolder, ChatMessage model, int
            position) {
        Context context=viewHolder.message.getContext();
        viewHolder.message.setText(model.getMessageText());
        viewHolder.time.setText("" + model.getMessageTime());
        if(model.getMessageUser().equals(FirebaseAuth.getInstance().getCurrentUser().getDisplayName())) {
            viewHolder.user.setText("you");
        }
        else{
            viewHolder.user.setText(model.getMessageUser());
        }
        if(model.isSent()){
            viewHolder.sentStatus.setVisibility(View.INVISIBLE);
        }
        else {
            viewHolder.sentStatus.setVisibility(View.VISIBLE);
        }
        if(model.isDelivered()){
            viewHolder.message.setTextColor(ContextCompat.getColor(context,R.color.delivered));
        }
        else{
            viewHolder.message.setTextColor(ContextCompat.getColor(context,R.color.notDelivered));
        }
    }

    public static class ChatMessageViewHolder extends RecyclerView.ViewHolder {
        TextView user, time, message;
        ImageView sentStatus;
        public ChatMessageViewHolder(View itemView) {
            super(itemView);
            user = (TextView) itemView.findViewById(R.id.message_user);
            time = (TextView) itemView.findViewById(R.id.message_time);
            message = (TextView) itemView.findViewById(R.id.message_text);
            sentStatus= (ImageView) itemView.findViewById(R.id.sent_status);
        }
    }

}
