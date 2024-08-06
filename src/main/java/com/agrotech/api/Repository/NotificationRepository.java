package com.agrotech.api.Repository;

import com.agrotech.api.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    Optional<Notification> findByMessage(String message);
    // Vous pouvez ajouter des méthodes de requête personnalisées ici, si nécessaire
}
