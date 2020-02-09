package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Course;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.StudentAssessmentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StudentAssessmentController {

    private StudentAssessmentService studentAssessmentService;

    public StudentAssessmentController(StudentAssessmentService studentAssessmentService) {
        this.studentAssessmentService = studentAssessmentService;
    }

    @GetMapping("/")
    public CommonResponse<ThirdKpi> getAllThirdKpi() {
        return this.studentAssessmentService.findAll();
    }

    @GetMapping("/{classId}")
    public CommonResponse<Course> getAllCourse(@PathVariable("classId") Integer classId) {
        return this.studentAssessmentService.currentCourse(classId);
    }


}
