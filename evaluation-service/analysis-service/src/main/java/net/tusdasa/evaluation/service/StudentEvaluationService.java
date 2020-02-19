package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.StudentEvaluation;

import java.util.List;

public interface StudentEvaluationService {

    StudentEvaluation addStudentCourseResult(StudentEvaluation studentEvaluation);

    void updateCourseResultTotal(StudentEvaluation studentEvaluation);

    List<StudentEvaluation> findAllByCourseId(Integer courseId);

    StudentEvaluation findAllById(String id);

    List<StudentEvaluation> findAllByCourseIdAndTermId(Integer courseId, Integer termId);

    List<StudentEvaluation> findAllByStudentId(Long studentId);

    List<StudentEvaluation> findAllByStudentIdAndAndTermId(Long studentId, Integer termId);
}
