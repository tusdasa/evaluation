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

    @GetMapping("/result/{termId}")
    public CommonResponse<Long> countAllByTermId(@PathVariable("termId") Integer termId) {
        return new CommonResponse<Long>().ok().data(this.studentEvaluationService.countAllByTermId(termId));
    }

    @DeleteMapping("/result/{termId}")
    public CommonResponse<String> deleteAllByTermId(@PathVariable("termId") Integer termId) {
        this.studentEvaluationService.deleteAllByTermId(termId);
        return new CommonResponse<String>().ok();
    }

    @GetMapping("/result/{departmentId}/{termId}")
    public CommonResponse<Long> countAllByTermIdAndAndDepartmentId(@PathVariable("termId") Integer termId, @PathVariable("departmentId") Integer departmentId) {
        return new CommonResponse<Long>().ok().data(
                this.studentEvaluationService.countAllByTermIdAndAndDepartmentId(
                        termId,
                        departmentId
                )
        );
    }


}
