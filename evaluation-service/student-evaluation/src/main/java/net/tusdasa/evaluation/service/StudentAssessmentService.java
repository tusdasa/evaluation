package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.ThirdKpi;

public interface StudentAssessmentService {

    CommonResponse<ThirdKpi> findAll();

    CommonResponse<Course> currentCourse(String studentId);

}
