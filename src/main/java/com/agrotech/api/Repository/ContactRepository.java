package com.agrotech.api.Repository;


import com.agrotech.api.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    Optional<Contact> findByCode(String code);
    Optional<Contact> findByCodeAndFarmer(String code,String farmer);
    @Query("{ 'isDeleted': ?0, $or: [ " +
            "{ 'firstName': { $regex: ?1, $options: 'i' } }, " +
            "{ 'lastName': { $regex: ?2, $options: 'i' } }, " +
            "{ 'code': { $regex: ?3, $options: 'i' } } " +
            "] }")
    Page<Contact> findByIsDeletedAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCodeContainingIgnoreCase(
            Boolean isDeleted,
            String firstName,
            String lastName,
            String code,
            Pageable pageable);
    @Query("{ 'isDeleted': ?0, 'farmer': ?4, $or: [ " +
            "{ 'firstName': { $regex: ?1, $options: 'i' } }, " +
            "{ 'lastName': { $regex: ?2, $options: 'i' } }, " +
            "{ 'code': { $regex: ?3, $options: 'i' } } " +
            "] }")
    Page<Contact> findByIsDeletedAndFarmerAndFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrCodeContainingIgnoreCase(
            Boolean isDeleted,
            String firstName,
            String lastName,
            String code,
            String farmer,
            Pageable pageable);

}
