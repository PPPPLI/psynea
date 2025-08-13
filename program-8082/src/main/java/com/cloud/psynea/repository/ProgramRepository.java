package com.cloud.psynea.repository;

import com.cloud.psynea.entity.Program;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends MongoRepository<Program,ObjectId>, ProgramRepositoryCustom {

    Program findProgramByUserName(String userName);
}
