package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.commons.FirstKpiResponse;
import net.tusdasa.evaluation.commons.SecondKpiResponse;
import net.tusdasa.evaluation.service.StudentAssessmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentAssessmentController {

    private StudentAssessmentService studentAssessmentService;

    public StudentAssessmentController(StudentAssessmentService studentAssessmentService) {
        this.studentAssessmentService = studentAssessmentService;
    }

    @GetMapping("/")
    public CommonResponse<FirstKpiResponse> getTest() {
        return new CommonResponse<FirstKpiResponse>().ok().table(this.studentAssessmentService.findAllThirdKpi());
    }
}
