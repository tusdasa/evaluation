package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.service.TeacherService;
import net.tusdasa.evaluation.vo.TeacherRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-09 8:56 AM
 */

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/{id}")
    public CommonResponse<Teacher> findById(@PathVariable("id") Integer id) {
        return this.teacherService.findById(id);
    }

    @GetMapping("/")
    public CommonResponse<String> update(@RequestBody TeacherRequest request) {
        return this.teacherService.update(request);
    }
}
