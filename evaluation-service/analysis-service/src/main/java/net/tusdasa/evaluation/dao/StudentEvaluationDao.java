package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.StudentEvaluation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentEvaluationDao extends MongoRepository<StudentEvaluation, String> {

    List<StudentEvaluation> findAllByCourseId(Integer courseId);

    Optional<StudentEvaluation> findAllById(String id);

    List<StudentEvaluation> findAllByCourseIdAndTermId(Integer courseId, Integer termId);

    List<StudentEvaluation> findAllByStudentId(Long studentId);

    List<StudentEvaluation> findAllByStudentIdAndAndTermId(Long studentId, Integer termId);

}
