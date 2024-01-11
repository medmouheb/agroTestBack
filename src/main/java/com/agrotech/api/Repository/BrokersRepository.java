package com.agrotech.api.Repository;

import com.agrotech.api.model.Brokers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrokersRepository extends MongoRepository<Brokers,String> {

    Optional<Brokers> findByBrokerCode(String BrokerCode);
    Page<Brokers> findByBrokerNameContainingIgnoreCase(String BrokerName, Pageable pageable);
    Page<Brokers> findByIsDeletedAndBrokerNameContainingIgnoreCase(Boolean isDeleted, String BrokersName, Pageable pageable);
    Page<Brokers> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Brokers findByBrokerName(String BrokerName );

}
