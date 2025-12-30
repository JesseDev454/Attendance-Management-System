package com.nileuniversity.tams.service;

import com.nileuniversity.tams.model.Notification;
import java.util.ArrayList;
import java.util.List;

/**
 * NotificationService
 * Manages system notifications
 * Uses in-memory storage
 */
public class NotificationService {
    private static NotificationService instance;
    private List<Notification> notifications;
    private int notificationCounter = 1;

    /**
     * Private constructor for Singleton pattern
     */
    private NotificationService() {
        notifications = new ArrayList<>();
    }

    /**
     * Get singleton instance
     */
    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    /**
     * Send absence notification
     */
    public void sendAbsenceNotification(String studentId, String courseName) {
        String notifId = "NOTIF" + String.format("%04d", notificationCounter++);
        String message = "You were marked absent for " + courseName;
        Notification notification = new Notification(notifId, message, studentId);
        notification.sendAlert();
        notifications.add(notification);
    }

    /**
     * Send late check-in notification
     */
    public void sendLateCheckInNotification(String studentId, String courseName) {
        String notifId = "NOTIF" + String.format("%04d", notificationCounter++);
        String message = "You checked in late for " + courseName;
        Notification notification = new Notification(notifId, message, studentId);
        notification.sendAlert();
        notifications.add(notification);
    }

    /**
     * Send custom notification
     */
    public void sendNotification(String recipientId, String message) {
        String notifId = "NOTIF" + String.format("%04d", notificationCounter++);
        Notification notification = new Notification(notifId, message, recipientId);
        notification.pushNotification();
        notifications.add(notification);
    }

    /**
     * Get notifications for a user
     */
    public List<Notification> getNotificationsForUser(String userId) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getRecipientId().equals(userId)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }

    /**
     * Get unread notifications for a user
     */
    public List<Notification> getUnreadNotifications(String userId) {
        List<Notification> unreadNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getRecipientId().equals(userId) && !notification.isRead()) {
                unreadNotifications.add(notification);
            }
        }
        return unreadNotifications;
    }

    /**
     * Mark notification as read
     */
    public boolean markAsRead(String notifId) {
        for (Notification notification : notifications) {
            if (notification.getNotifId().equals(notifId)) {
                notification.setRead(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Mark all notifications as read for a user
     */
    public void markAllAsRead(String userId) {
        for (Notification notification : notifications) {
            if (notification.getRecipientId().equals(userId)) {
                notification.setRead(true);
            }
        }
    }

    /**
     * Delete notification
     */
    public boolean deleteNotification(String notifId) {
        return notifications.removeIf(notification -> notification.getNotifId().equals(notifId));
    }

    /**
     * Get all notifications
     */
    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notifications);
    }

    /**
     * Get unread count for user
     */
    public int getUnreadCount(String userId) {
        return getUnreadNotifications(userId).size();
    }

    /**
     * Clear all notifications for a user
     */
    public void clearNotifications(String userId) {
        notifications.removeIf(notification -> notification.getRecipientId().equals(userId));
    }
}
