package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.entity.TeacherEvaluation;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.TeacherEvaluationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherEvaluationController {

    private TeacherEvaluationService teacherEvaluationService;

    public TeacherEvaluationController(TeacherEvaluationService teacherEvaluationService) {
        this.teacherEvaluationService = teacherEvaluationService;
    }

    @GetMapping("/")
    public CommonResponse<ThirdKpi> findKpiAll(@RequestHeader(name = "role") Integer role) {
        return teacherEvaluationService.findAllThirdKpi(role);
    }

    @GetMapping("/teacher")
    public CommonResponse<Teacher> findTeacher(@RequestHeader(name = "workId") Integer workId, @RequestHeader(name = "role") Integer role) {
        return teacherEvaluationService.findAllTeacher(workId, role);
    }

    @PostMapping("/result")
    public CommonResponse<String> addTeacherResult(@RequestBody TeacherEvaluation teacherEvaluation, @RequestHeader(name = "workId") Integer workId, @RequestHeader(name = "role") Integer role) {
        teacherEvaluationService.addTeacherEvaluation(teacherEvaluation, workId, role);
        System.out.println(teacherEvaluation.toString());
        return new CommonResponse<String>().ok();
    }

}
