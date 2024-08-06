package com.agrotech.api.controller;

import com.agrotech.api.model.Notification;
import com.agrotech.api.services.impl.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('FARMER') or hasRole('ADMIN')")

    @PostMapping
    public void addNotification(@RequestParam String message) {
        notificationService.addNotification(message);
    }

    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getNotifications();
    }

    @DeleteMapping("/{id}")
    public boolean deleteNotificationById(@PathVariable String id) {
        return notificationService.deleteNotificationById(id);
    }


    @DeleteMapping
    public void deleteAllNotifications() {
        notificationService.deleteAllNotifications();
    }


    @GetMapping("/count")
    public long getNotificationCount() {
        return notificationService.getNotificationCount();
    }
}
