package com.agrotech.api.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.MvtStk;

import java.util.Date;
import java.util.Optional;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

@Repository
public interface MvtStkRepository extends MongoRepository<MvtStk, String>{
    Optional<MvtStk> findByCodeAndFarmer(String code , String farmer);
    Page<MvtStk> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<MvtStk> findByIsDeletedAndCodeContainingIgnoreCase(Boolean isDeleted, String code, Pageable pageable);
    Page<MvtStk> findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(Boolean isDeleted, String code,String farmer, Pageable pageable);


    @Query("{ 'farmer': ?0, 'isDeleted': false, 'dateMvt': { $gte: ?1, $lt: ?2 } }")
    List<MvtStk> findByFarmerAndMonthAndYear(String farmer, Date startDate, Date endDate);
}
