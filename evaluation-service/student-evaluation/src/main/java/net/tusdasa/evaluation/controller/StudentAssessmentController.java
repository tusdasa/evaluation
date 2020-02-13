package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.StudentCourseResult;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.StudentAssessmentService;
import net.tusdasa.evaluation.service.StudentCourseResultService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class StudentAssessmentController {


    private StudentAssessmentService studentAssessmentService;

    private StudentCourseResultService studentCourseResultService;

    public StudentAssessmentController(StudentAssessmentService studentAssessmentService, StudentCourseResultService studentCourseResultService) {
        this.studentAssessmentService = studentAssessmentService;
        this.studentCourseResultService = studentCourseResultService;
    }

    @GetMapping("/")
    public CommonResponse<ThirdKpi> getAllThirdKpi() {
        return this.studentAssessmentService.findAll();
    }

    @GetMapping("/course")
    public CommonResponse<Course> getAllCourse(@RequestHeader(name = "studentId") String studentId) {
        return this.studentAssessmentService.currentCourse(studentId);
    }

    @PostMapping("/result")
    public CommonResponse<String> addCourseResult(@RequestBody StudentCourseResult studentCourseResult, @RequestHeader(name = "studentId") Long studentId) {
        studentCourseResultService.addStudentCourseResult(studentCourseResult, studentId);
        return new CommonResponse<String>().ok();
    }


}
