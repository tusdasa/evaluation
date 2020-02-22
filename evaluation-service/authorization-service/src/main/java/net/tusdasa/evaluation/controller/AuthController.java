package net.tusdasa.evaluation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.service.StudentAuthService;
import net.tusdasa.evaluation.service.TeacherAuthService;
import net.tusdasa.evaluation.utils.JWTUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@RestController
@Api(value = "认证API")
public class AuthController {

    private JWTUtils jwtUtils;

    private StudentAuthService studentAuthService;

    private TeacherAuthService teacherAuthService;

    private RedisTemplate<String, Student> studentRedisTemplate;

    private RedisTemplate<String, Teacher> teacherRedisTemplate;

    public AuthController(JWTUtils jwtUtils, StudentAuthService studentAuthService, TeacherAuthService teacherAuthService, RedisTemplate<String, Student> studentRedisTemplate, RedisTemplate<String, Teacher> teacherRedisTemplate) {
        this.jwtUtils = jwtUtils;
        this.studentAuthService = studentAuthService;
        this.teacherAuthService = teacherAuthService;
        this.studentRedisTemplate = studentRedisTemplate;
        this.teacherRedisTemplate = teacherRedisTemplate;
    }

    @PostMapping("/student")
    @ApiImplicitParam(paramType = "query", name = "studentId", value = "用户名", dataType = "long")
    @ApiOperation(value = "用户认证")
    public CommonResponse<String> authStudent(@RequestParam("studentId") String studentId, @RequestParam("password") String password) {
        Map<String, Object> result = studentAuthService.findStudent(Long.valueOf(studentId), password);
        if (result.get("obj") != null) {
            Student student = (Student) result.get("obj");
            studentRedisTemplate.opsForValue().set(student.getStudentId().toString(), student, Duration.of(1, ChronoUnit.HOURS));
            return new CommonResponse<String>().ok().data(jwtUtils.generateNewToken(student.getStudentId(), 1));
        }
        return new CommonResponse<String>().error(result.get("msg").toString());
    }

    @PostMapping("/teacher")
    public CommonResponse<String> authTeacher(@RequestParam("workId") String workId, @RequestParam("password") String password) {
        Map<String, Object> result = teacherAuthService.findTeacher(Integer.valueOf(workId), password);
        if (result.get("obj") != null) {
            Teacher teacher = (Teacher) result.get("obj");
            teacherRedisTemplate.opsForValue().set(teacher.getWorkId().toString(), teacher, Duration.of(1, ChronoUnit.HOURS));
            return new CommonResponse<String>().ok().data(jwtUtils.generateNewToken(teacher.getWorkId().longValue(), teacher.getRoleId()));
        }
        return new CommonResponse<String>().error(result.get("msg").toString());
    }
/*
    @PostMapping("/authorization")
    public CommonResponse<String> authorization(
            @RequestParam(name = "studentId", required = false) String studentId,
            @RequestParam(name = "workId", required = false) String workId,
            @RequestParam("password") String password
    ){
        if (CheckUtils.isString(studentId)){
            Map<String, Object> studentResult = studentAuthService.findStudent(Long.valueOf(studentId), password);
            if (null != studentResult.get("obj")) {
                Student student = (Student) studentResult.get("obj");
                studentRedisTemplate.opsForValue().set(student.getStudentId().toString(), student, Duration.of(1, ChronoUnit.HOURS));
                return new CommonResponse<String>().ok().data(jwtUtils.generateNewToken(student.getStudentId(), 1));
            }
            return new CommonResponse<String>().error(studentResult.get("msg").toString());
        }else if (CheckUtils.isString(workId)){
            Map<String, Object> teacherResult = teacherAuthService.findTeacher(Integer.valueOf(workId), password);
            if (null != teacherResult.get("obj")) {
                Teacher teacher = (Teacher) teacherResult.get("obj");
                teacherRedisTemplate.opsForValue().set(teacher.getWorkId().toString(), teacher, Duration.of(1, ChronoUnit.HOURS));
                return new CommonResponse<String>().ok().data(jwtUtils.generateNewToken(teacher.getWorkId().longValue(), teacher.getRoleId()));
            }
            return new CommonResponse<String>().error(teacherResult.get("msg").toString());
        }else {
            return new CommonResponse<String>();
        }
    }

 */
}
