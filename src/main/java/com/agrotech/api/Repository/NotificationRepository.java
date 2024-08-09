package com.agrotech.api.Repository;

import com.agrotech.api.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, String> {
    Optional<Notification> findByMessage(String message);

    void save(Notification newNotification);

    boolean existsById(String id);

    void deleteById(String id);

    void deleteAll();

    long count();
    // Vous pouvez ajouter des méthodes de requête personnalisées ici, si nécessaire
}
