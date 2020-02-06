package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.commons.FirstKpiResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;

import java.util.List;

public interface StudentAssessmentService {

    List<FirstKpiResponse> findAllThirdKpi();

    CommonResponse<ThirdKpi> findAll();

}
