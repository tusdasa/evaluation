package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.StudentEvaluationInfoService;
import net.tusdasa.evaluation.service.StudentEvaluationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentEvaluationController {


    private StudentEvaluationInfoService studentEvaluationInfoService;

    private StudentEvaluationService studentEvaluationService;

    public StudentEvaluationController(StudentEvaluationInfoService studentEvaluationInfoService, StudentEvaluationService studentEvaluationService) {
        this.studentEvaluationInfoService = studentEvaluationInfoService;
        this.studentEvaluationService = studentEvaluationService;
    }

    @GetMapping("/")
    public CommonResponse<ThirdKpi> getAllThirdKpi(@RequestHeader(name = "role") Integer role) {
        return this.studentEvaluationInfoService.findAll(role);
    }

    @GetMapping("/course")
    public CommonResponse<Course> getAllCourse(@RequestHeader(name = "studentId") String studentId) {
        return this.studentEvaluationInfoService.currentCourse(studentId);
    }

    @PostMapping("/result")
    public CommonResponse<String> addCourseResult(@RequestBody StudentEvaluation studentEvaluation, @RequestHeader(name = "studentId") Long studentId) {
        studentEvaluationService.addStudentCourseResult(studentEvaluation, studentId);
        return new CommonResponse<String>().ok();
    }


}
