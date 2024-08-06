package com.agrotech.api.services.impl;

import com.agrotech.api.Repository.NotificationRepository;
import com.agrotech.api.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class NotificationService {
    private static final Logger logger = Logger.getLogger("myLogger");

    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public void addNotification(String message) {
        // Create a new Notification object with the message and current timestamp
        Notification newNotification = new Notification(message, LocalDateTime.now());

        // Check if a notification with the same message already exists
        Optional<Notification> existingNotification = notificationRepository.findByMessage(message);
        if (existingNotification.isPresent()) {
            logger.info("Notification with message \"" + newNotification.getMessage() + "\" already exists");
            return; // Do not add the duplicate notification
        }

        logger.info("Adding notification: " + newNotification.getMessage());

        // Save to MongoDB
        notificationRepository.save(newNotification);
    }

    public List<Notification> getNotifications() {
        logger.info("Retrieving notifications");

        // Fetch notifications from MongoDB
        return notificationRepository.findAll();
    }

    @Transactional
    public boolean deleteNotificationById(String id) {
        logger.info("Deleting notification with id: " + id);

        // Check if the notification exists
        if (!notificationRepository.existsById(id)) {
            logger.info("Notification with id " + id + " not found.");
            return false;
        }

        // Delete the notification
        notificationRepository.deleteById(id);
        logger.info("Notification with id " + id + " deleted successfully.");
        return true;
    }

    @Transactional
    public void deleteAllNotifications() {
        logger.info("Deleting all notifications");
        notificationRepository.deleteAll();
    }

    public long getNotificationCount() {
        long count = notificationRepository.count();
        logger.info("Notification count: " + count);
        return count;
    }
}
