package com.agrotech.api.Repository;


import com.agrotech.api.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository  extends MongoRepository<Task ,String> {

    List<Task> findByTasksOwnerContainingIgnoreCaseAndActurContainingIgnoreCaseAndIsDeleted(String owner, String actur , Boolean deleted);
    List<Task> findByTasksOwnerContainingIgnoreCaseAndIsDeleted(String owner, Boolean deleted);
}
