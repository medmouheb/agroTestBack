package com.agrotech.api.Repository;

import java.util.List;
import java.util.Optional;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.ERole;
import com.agrotech.api.model.Role;
import com.agrotech.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Page<User> findByFarmerAndUsernameContainingIgnoreCase(String id,String  username,Pageable pageable);
    Page<User>  findByRolesContainingAndUsernameContainingIgnoreCase(Role r , String username,Pageable pageable);

//    Page<Campany> findByNameContainingIgnoreCase(String name, Pageable pageable);


}