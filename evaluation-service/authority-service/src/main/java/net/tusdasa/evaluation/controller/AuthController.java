package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.service.StudentAuthService;
import net.tusdasa.evaluation.service.TeacherAuthService;
import net.tusdasa.evaluation.utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    private JWTUtils jwtUtils;

    private StudentAuthService studentAuthService;

    private TeacherAuthService teacherAuthService;

    public AuthController(JWTUtils jwtUtils, StudentAuthService studentAuthService, TeacherAuthService teacherAuthService) {
        this.jwtUtils = jwtUtils;
        this.studentAuthService = studentAuthService;
        this.teacherAuthService = teacherAuthService;
    }

    @PostMapping("/student")
    public CommonResponse<String> authStudent(@RequestParam("studentId") Long studentId, @RequestParam("password") String password) {
        Map<String, Object> result = studentAuthService.findStudent(studentId, password);
        if (result.get("obj") != null) {
            Student student = (Student) result.get("obj");
            return new CommonResponse<String>().ok().data(jwtUtils.generateToken(student.getStudentId()));
        }
        return new CommonResponse<String>().error(result.get("msg").toString());

    }

    @PostMapping("/teacher")
    public CommonResponse<String> authTeacher(@RequestParam("workId") Integer workId, @RequestParam("password") String password) {
        Map<String, Object> result = teacherAuthService.findTeacher(workId, password);

        if (result.get("obj") != null) {
            Teacher teacher = (Teacher) result.get("obj");
            return new CommonResponse<String>().ok().data(jwtUtils.generateToken(teacher.getRoleId().longValue()));
        }
        return new CommonResponse<String>().error(result.get("msg").toString());

    }
}
