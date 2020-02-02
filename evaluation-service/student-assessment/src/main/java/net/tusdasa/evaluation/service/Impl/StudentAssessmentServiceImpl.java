package net.tusdasa.evaluation.service.Impl;

import net.tusdasa.evaluation.kpi.FirstKpiResponse;
import net.tusdasa.evaluation.service.StudentAssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAssessmentServiceImpl implements StudentAssessmentService {

    private final Integer STUDENT_FIRST_KPI = 5;

    public StudentAssessmentServiceImpl() {

    }

    @Override
    public List<FirstKpiResponse> findAllSecondKpi(Integer firstKpiId) {
        return null;
    }
}
