package com.karan.chatdemo.model;

import java.util.Date;

/**
 * Created by stpl on 3/30/2017.
 */

public class ChatMessage {

    private String messageText;
    private String messageUser;
    private long messageTime;
    private boolean delivered,sent;

    public ChatMessage(String messageText, String messageUser, boolean delivered, boolean sent) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.delivered = delivered;
        this.sent = sent;

        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public ChatMessage() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
