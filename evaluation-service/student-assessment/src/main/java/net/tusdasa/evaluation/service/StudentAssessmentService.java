package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.entity.ThirdKpi;

import java.util.List;

public interface StudentAssessmentService {

    List<FirstKpi> findAllFirstKpi(Integer roleId);

    List<SecondKpi> findAllSecondKpi(Integer firstKpiId);

    List<ThirdKpi> findAllThirdKpi(Integer secondKpiId);

}
