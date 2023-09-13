package com.agrotech.api.Repository;

import com.agrotech.api.model.FileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<FileDocument, String> {
}