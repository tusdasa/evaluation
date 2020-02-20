package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.TeacherEvaluation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherEvaluationDao extends MongoRepository<TeacherEvaluation, String> {
}
