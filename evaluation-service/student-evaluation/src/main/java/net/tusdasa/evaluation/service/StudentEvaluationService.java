package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.StudentEvaluation;

import java.util.List;

public interface StudentEvaluationService {

    void addStudentCourseResult(StudentEvaluation studentEvaluation, Long studentId);

    List<StudentEvaluation> findBydStudentId(Long studentId);
}
