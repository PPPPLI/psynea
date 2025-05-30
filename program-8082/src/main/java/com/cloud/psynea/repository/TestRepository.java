package com.cloud.psynea.repository;

import com.cloud.psynea.entity.Question;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Question, ObjectId> {

    Question findQuestionByIndex(Integer index);
}
