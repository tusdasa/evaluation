package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.kpi.FirstKpiResponse;

import java.util.List;

public interface StudentAssessmentService {

    /*
    List<FirstKpi> findAllFirstKpi(Integer roleId);

    List<SecondKpi> findAllSecondKpi(Integer firstKpiId);

    List<ThirdKpi> findAllThirdKpi(Integer secondKpiId);

     */
    List<FirstKpiResponse> findAllSecondKpi(Integer firstKpiId);


}
