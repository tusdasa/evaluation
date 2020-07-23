package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.service.StudentClassService;
import net.tusdasa.evaluation.vo.StudentClassRequest;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentClassController {

    private StudentClassService studentClassService;

    public StudentClassController(StudentClassService studentClassService) {
        this.studentClassService = studentClassService;
    }

    @GetMapping("/class")
    public CommonResponse<StudentClass> findAllStudentClass() {
        return new CommonResponse<StudentClass>().ok().table(this.studentClassService.findAll());
    }

    @GetMapping("/class/{studentClassId}")
    public CommonResponse<StudentClass> findByStudentClassId(@PathVariable("studentClassId") Integer studentClassId) {
        StudentClass studentClass = this.studentClassService.findStudentClassById(studentClassId);
        if (studentClass != null) {
            return new CommonResponse<StudentClass>().ok().data(studentClass);
        }
        return new CommonResponse<StudentClass>().error("未找到");
    }

    @PostMapping("/class")
    public CommonResponse<String> createStudentClass(@RequestBody StudentClassRequest request) {
        this.studentClassService.addStudentClass(request);
        return new CommonResponse<String>().ok();
    }

    @PutMapping("/class")
    public CommonResponse<String> updateStudentClass(@RequestBody StudentClassRequest request) {
        this.studentClassService.updateStudentClass(request);
        return new CommonResponse<String>().ok();
    }


}
