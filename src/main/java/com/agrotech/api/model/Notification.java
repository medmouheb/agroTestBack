package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id; // Utiliser String ou ObjectId

    private String message;
    private LocalDateTime timestamp;

    public Notification(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
