package com.karan.chatdemo.model;

import java.util.Date;

/**
 * Created by stpl on 3/30/2017.
 */

public class ChatMessage {

    private String messageText;
    private String messageUser;
    private long messageTime;
    private boolean deliveryReport;

    public ChatMessage(String messageText, String messageUser, boolean deliveryReport) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.deliveryReport = deliveryReport;

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

    public boolean isDeliveryReport() {
        return deliveryReport;
    }

    public void setDeliveryReport(boolean deliveryReport) {
        this.deliveryReport = deliveryReport;
    }
}
