package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.commons.FirstKpiResponse;
import net.tusdasa.evaluation.commons.SecondKpiResponse;

import java.util.List;

public interface StudentAssessmentService {

    List<FirstKpiResponse> findAllSecondKpi(Integer firstKpiId);

    List<SecondKpiResponse> findAllThirdKpi(Integer secondKpiId);

}
