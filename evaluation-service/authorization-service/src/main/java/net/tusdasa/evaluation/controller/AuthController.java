package net.tusdasa.evaluation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.tusdasa.evaluation.authority.Authority;
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
    @ApiOperation(value = "学生认证")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "studentId", value = "学号", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", dataType = "String")
    })
    public CommonResponse<String> authStudent(@RequestParam("studentId") String studentId, @RequestParam("password") String password) {
        Map<String, Object> result = studentAuthService.findStudent(Long.valueOf(studentId), password);
        if (result.get("obj") != null) {
            Student student = (Student) result.get("obj");
            studentRedisTemplate.opsForValue().set(student.getStudentId().toString(), student, Duration.of(1, ChronoUnit.HOURS));
            return new CommonResponse<String>().ok().data(jwtUtils.generateNewToken(student.getStudentId(), Authority.STUDENT));
        }
        return new CommonResponse<String>().auth(result.get("msg").toString());
    }

    @ApiOperation(value = "教师认证")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "workId", value = "工号", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", dataType = "String")
    })
    @PostMapping("/teacher")
    public CommonResponse<String> authTeacher(@RequestParam("workId") String workId, @RequestParam("password") String password) {
        Map<String, Object> result = teacherAuthService.findTeacher(Integer.valueOf(workId), password);
        if (result.get("obj") != null) {
            Teacher teacher = (Teacher) result.get("obj");
            teacherRedisTemplate.opsForValue().set(teacher.getWorkId().toString(), teacher, Duration.of(1, ChronoUnit.HOURS));
            return new CommonResponse<String>().ok().data(jwtUtils.generateNewToken(teacher.getWorkId().longValue(), teacher.getRoleId()));
        }
        return new CommonResponse<String>().auth(result.get("msg").toString());
    }
}
