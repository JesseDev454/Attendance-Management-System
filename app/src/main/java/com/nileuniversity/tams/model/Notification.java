package com.nileuniversity.tams.model;

/**
 * Notification class
 * Represents system notifications for users
 */
public class Notification {
    private String notifId;
    private String message;
    private boolean isRead;
    private String recipientId;

    /**
     * Constructor
     */
    public Notification(String notifId, String message, String recipientId) {
        this.notifId = notifId;
        this.message = message;
        this.recipientId = recipientId;
        this.isRead = false;
    }

    /**
     * Send alert notification
     */
    public void sendAlert() {
        // Logic to send notification

    }

    /**
     * Push notification to user
     */
    public void pushNotification() {
        // Logic to push notification

    }

    // Getters and Setters
    public String getNotifId() {
        return notifId;
    }

    public void setNotifId(String notifId) {
        this.notifId = notifId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }
}
