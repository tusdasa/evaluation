package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.service.StudentClassService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/class")
public class StudentClassController {

    private StudentClassService studentClassService;

    public StudentClassController(StudentClassService studentClassService){
        this.studentClassService = studentClassService;
    }

    @GetMapping("/")
    public CommonResponse<StudentClass> findAll(){
        return new CommonResponse<StudentClass>().ok().table(this.studentClassService.findAll());
    }

    @GetMapping("/{id}")
    public CommonResponse<StudentClass> findById(@PathVariable Integer id){
        StudentClass studentClass = this.studentClassService.findStudentClassById(id);
        if (studentClass!=null){
            return new CommonResponse<StudentClass>().ok().data(studentClass);
        }
        return new CommonResponse<StudentClass>().error("未找到");
    }

}
