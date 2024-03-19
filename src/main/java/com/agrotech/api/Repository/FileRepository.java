package com.agrotech.api.Repository;

import com.agrotech.api.model.FileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileRepository extends MongoRepository<FileDocument, String> {

    List<FileDocument> findAllByFarmerContainingIgnoreCase(String s);
}