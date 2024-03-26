package com.agrotech.api.Repository;


import com.agrotech.api.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Optional<Contact> findByCode(String code);
    Page<Contact> findByIsDeletedAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCodeContainingIgnoreCase(Boolean isDeleted, String firstName,String lastName,String code, Pageable pageable);

    Page<Contact> findByIsDeletedAndFarmerAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCodeContainingIgnoreCase(Boolean isDeleted, String firstName,String lastName,String code,String farmer, Pageable pageable);


}
