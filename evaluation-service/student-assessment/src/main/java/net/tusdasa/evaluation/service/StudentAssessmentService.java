package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.entity.ThirdKpi;

import java.util.List;

public interface StudentAssessmentService {
    List<ThirdKpi> findAllThirdKpi(Integer secondKpiId);

    List<SecondKpi> findAllSecondKpi(Integer firstKpiId);
}
