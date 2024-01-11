package com.agrotech.api.Repository;

import com.agrotech.api.model.ERole;
import com.agrotech.api.model.Role;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
