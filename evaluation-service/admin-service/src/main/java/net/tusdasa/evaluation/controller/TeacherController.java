package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.service.TeacherService;
import net.tusdasa.evaluation.vo.TeacherRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 教师管理
 *
 * @Author: tusdasa
 * @Date: 2020-03-09 8:56 AM
 */

@RestController
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/teacher/{id}")
    public CommonResponse<Teacher> findById(@PathVariable("id") Integer id) {
        return this.teacherService.findById(id);
    }

    @PutMapping("/teacher")
    public CommonResponse<String> update(@RequestBody TeacherRequest request) {
        return this.teacherService.update(request);
    }

    @GetMapping("/teacher/department")
    public CommonResponse<Teacher> findByTeacherByPage(@RequestHeader("workId") Integer id,
                                                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (page < 0 || size <= 0) {
            page = 0;
            size = 10;
        }
        return this.teacherService.findByDepartmentByPage(id, page * size, size);
    }

}
