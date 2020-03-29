package net.tusdasa.evaluation.mongodb;

import net.tusdasa.evaluation.entity.TeacherEvaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tusdasa
 * @version 1.0
 * @Description 教学督导评价结果储存
 */
@Repository
public interface TeacherEvaluationDao extends MongoRepository<TeacherEvaluation, String> {
}
