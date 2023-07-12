package com.agrotech.api.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.MvtStk;


@Repository
public interface MvtStkRepository extends MongoRepository<MvtStk, String>{

}
