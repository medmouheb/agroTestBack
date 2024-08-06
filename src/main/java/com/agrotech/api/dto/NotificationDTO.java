package com.agrotech.api.dto;

import java.time.LocalDateTime;

public class NotificationDTO extends BaseDto {
    private long id;
    private String message;
    private LocalDateTime timestamp;

    // Default constructor
    public NotificationDTO() {}

    // Parameterized constructor
    public NotificationDTO(long id, String message, LocalDateTime timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and setters


    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
