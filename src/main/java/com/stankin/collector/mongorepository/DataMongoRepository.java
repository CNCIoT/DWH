package com.stankin.collector.mongorepository;

import com.stankin.collector.document.DataDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataMongoRepository extends MongoRepository<DataDocument, String> {}
