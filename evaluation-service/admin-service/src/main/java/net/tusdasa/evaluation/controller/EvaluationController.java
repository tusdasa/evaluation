package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.service.EvaluationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-03-29 2:01 PM
 */

@RestController
public class EvaluationController {

    EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("/evaluation/scount")
    public CommonResponse<Long> getAllStudent() {
        return evaluationService.getAllStudent();
    }

    @GetMapping("/evaluation/tcount")
    public CommonResponse<Long> getAllTeacher() {
        return evaluationService.getAllTeacher();
    }

    @DeleteMapping("/evaluation/teacher")
    public CommonResponse<String> deleteAllTeacher(@RequestHeader("workId") Integer workId) {
        System.out.println(workId + "deleteAllTeacher");
        return evaluationService.deleteAllTeacher();
    }

    @DeleteMapping("/evaluation/student")
    public CommonResponse<String> deleteAllStudent(@RequestHeader("workId") Integer workId) {
        System.out.println(workId + "deleteAllStudent");
        return evaluationService.deleteAllStudent();
    }
}
