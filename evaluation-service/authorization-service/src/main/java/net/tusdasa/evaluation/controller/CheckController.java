package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.commons.Token;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.utils.JWTUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/check")
public class CheckController {

    private RedisTemplate<String, Student> studentRedisTemplate;

    private RedisTemplate<String, Teacher> teacherRedisTemplate;

    private JWTUtils jwtUtils;

    public CheckController(RedisTemplate<String, Student> studentRedisTemplate, RedisTemplate<String, Teacher> teacherRedisTemplate, JWTUtils jwtUtils) {
        this.studentRedisTemplate = studentRedisTemplate;
        this.teacherRedisTemplate = teacherRedisTemplate;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/student")
    public CommonResponse<Student> checkStudent(@RequestParam("token") String token) {
        Map<String, Object> result = this.jwtUtils.check(token);
        if (result.get("code").equals(200)) {
            Token t = (Token) result.get("token");
            Student student = studentRedisTemplate.opsForValue().get(t.getSub());
            return new CommonResponse<Student>().ok().data(student);
        } else {
            return new CommonResponse<Student>().error("认证失败,请重新登录");
        }
    }

    @PostMapping("/teacher")
    public CommonResponse<Teacher> checkTeacher(@RequestParam("token") String token) {
        Map<String, Object> result = this.jwtUtils.check(token);
        if (result.get("code").equals(200)) {
            Token t = (Token) result.get("token");
            Teacher teacher = teacherRedisTemplate.opsForValue().get(t.getSub());
            return new CommonResponse<Teacher>().ok().data(teacher);
        } else {
            return new CommonResponse<Teacher>().error("认证失败,请重新登录");
        }
    }
}
