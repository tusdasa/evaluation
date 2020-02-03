package net.tusdasa.evaluation.controller;

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
    public String getTest() {
        studentAssessmentService.findAllThirdKpi();
        return "111";
    }
}
