package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.TeacherEvaluationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherEvaluationController {

    private TeacherEvaluationService teacherEvaluationService;

    public TeacherEvaluationController(TeacherEvaluationService teacherEvaluationService) {
        this.teacherEvaluationService = teacherEvaluationService;
    }

    @GetMapping("/")
    public CommonResponse<ThirdKpi> findAll(@RequestHeader(name = "role") Integer role) {
        return teacherEvaluationService.findAllThirdKpi(role);
    }

}
