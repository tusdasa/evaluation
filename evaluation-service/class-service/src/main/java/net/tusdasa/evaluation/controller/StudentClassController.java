package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.service.StudentClassService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentClassController {

    private StudentClassService studentClassService;

    public StudentClassController(StudentClassService studentClassService) {
        this.studentClassService = studentClassService;
    }

    @GetMapping("/")
    public CommonResponse<StudentClass> findAll() {
        return new CommonResponse<StudentClass>().ok().table(this.studentClassService.findAll());
    }

    @GetMapping("/{studentClassId}")
    public CommonResponse<StudentClass> findByStudentClassId(@PathVariable("studentClassId") Integer studentClassId) {
        StudentClass studentClass = this.studentClassService.findStudentClassById(studentClassId);
        if (studentClass != null) {
            return new CommonResponse<StudentClass>().ok().data(studentClass);
        }
        return new CommonResponse<StudentClass>().error("未找到");
    }

}
