package net.tusdasa.evaluation.mongodb;

import net.tusdasa.evaluation.entity.StudentEvaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEvaluationDao extends MongoRepository<StudentEvaluation, String> {
}
