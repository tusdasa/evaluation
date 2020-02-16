package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.StudentCourseResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCourseResultDao extends MongoRepository<StudentCourseResult, String> {

    List<StudentCourseResult> findAllByCourseId(Integer courseId);

    Optional<StudentCourseResult> findAllById(String id);

    List<StudentCourseResult> findAllByCourseIdAndTermId(Integer courseId, Integer termId);

    List<StudentCourseResult> findAllByStudentId(Long studentId);

    List<StudentCourseResult> findAllByStudentIdAndAndTermId(Long studentId, Integer termId);

}
