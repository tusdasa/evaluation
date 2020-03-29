package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.service.ClassService;
import net.tusdasa.evaluation.vo.StudentClassRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-16 4:36 PM
 */

@RestController
public class ClassController {

    private ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/class")
    public CommonResponse<StudentClass> findAllStudentClass() {
        return this.classService.findAllStudentClass();
    }

    @GetMapping("/class/{id}")
    public CommonResponse<StudentClass> findByStudentClassId(@PathVariable("id") Integer id) {
        return this.classService.findByStudentClassId(id);
    }

    @PostMapping("/class")
    public CommonResponse<String> createStudentClass(@RequestBody StudentClassRequest request) {
        return this.classService.createStudentClass(request);
    }

    @PutMapping("/class")
    public CommonResponse<String> updateStudentClass(@RequestBody StudentClassRequest request) {
        return this.classService.updateStudentClass(request);
    }

}
