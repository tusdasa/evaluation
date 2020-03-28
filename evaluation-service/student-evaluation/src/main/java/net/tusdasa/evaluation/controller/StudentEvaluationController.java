package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.StudentEvaluation;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.StudentEvaluationService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentEvaluationController {



    private StudentEvaluationService studentEvaluationService;

    public StudentEvaluationController(StudentEvaluationService studentEvaluationService) {
        this.studentEvaluationService = studentEvaluationService;
    }

    @GetMapping("/")
    public CommonResponse<ThirdKpi> getAllThirdKpi(@RequestHeader(name = "role") Integer role) {
        return this.studentEvaluationService.findAll(role);
    }

    @GetMapping("/course")
    public CommonResponse<Course> getAllCourse(@RequestHeader(name = "studentId") Long studentId) {
        return this.studentEvaluationService.currentCourse(studentId);
    }

    @PostMapping("/result")
    public CommonResponse<String> addCourseResult(@RequestBody StudentEvaluation studentEvaluation, @RequestHeader(name = "studentId") Long studentId) {
        return studentEvaluationService.addStudentCourseResult(studentEvaluation, studentId);
    }


}
