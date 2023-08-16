package com.agrotech.api.Repository;

import com.agrotech.api.model.Reason;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReasonRepo extends MongoRepository<Reason, String> {
    boolean existsByReasonCode(String reasonCode);
    Reason findByReasonCode(String reasonCode);
    List<Reason>findReasonByReasonNameContainingIgnoreCaseAndActiveTrue(String reasonName);
    List<Reason>findReasonByReasonNameContainingIgnoreCaseAndActiveFalse(String reasonName);

    List<Reason>findReasonByActiveTrue();
    List<Reason>findReasonByActiveFalse();

}
