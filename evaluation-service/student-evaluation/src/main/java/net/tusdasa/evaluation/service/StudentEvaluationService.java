package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.entity.ThirdKpi;

public interface StudentEvaluationService {

    CommonResponse<String> addStudentCourseResult(StudentEvaluation studentEvaluation, Long studentId);

    CommonResponse<ThirdKpi> findAll(Integer role);

    CommonResponse<Course> currentCourse(Long studentId);

}
