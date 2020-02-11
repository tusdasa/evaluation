package net.tusdasa.evaluation.dao;

import net.tusdasa.evaluation.entity.StudentCourseResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCourseResultDao extends MongoRepository<StudentCourseResult, Integer> {
    List<StudentCourseResult> findAllByCourseId(Integer courseId);

    List<StudentCourseResult> findAllByWorkId(Integer workId);

    Optional<StudentCourseResult> findByStudentId(Long studentId);

    List<StudentCourseResult> findAllByCourseIdAndTermId(Integer courseId, Integer termId);

    List<StudentCourseResult> findAllByWorkIdAndTermId(Integer workId, Integer termId);

}
