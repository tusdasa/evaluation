package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.ThirdKpi;

public interface StudentEvaluationInfoService {

    CommonResponse<ThirdKpi> findAll(Integer role);

    CommonResponse<Course> currentCourse(Long studentId);

}
