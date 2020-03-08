package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.service.StudentService;
import net.tusdasa.evaluation.vo.StudentRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-08 8:58 PM
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public CommonResponse<Student> findById(@PathVariable("id") Long id) {
        return this.studentService.findById(id);
    }

    @PutMapping("/")
    public CommonResponse<String> update(@RequestBody StudentRequest request) {
        return this.studentService.update(request);
    }
}
