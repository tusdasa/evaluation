package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    private RedisTemplate<String, Student> studentRedisTemplate;

    private RedisTemplate<String, Teacher> teacherRedisTemplate;


    public CheckController(RedisTemplate<String, Student> studentRedisTemplate, RedisTemplate<String, Teacher> teacherRedisTemplate) {
        this.studentRedisTemplate = studentRedisTemplate;
        this.teacherRedisTemplate = teacherRedisTemplate;
    }

    @PostMapping("/check/student")
    public CommonResponse<Student> checkStudent(@RequestParam("studentId") String studentId) {
        Student student = studentRedisTemplate.opsForValue().get(studentId.toString());
        if (student != null) {
            return new CommonResponse<Student>().ok().data(student);
        } else {
            return new CommonResponse<Student>().error("认证失败,请重新登录");
        }
    }

    @PostMapping("/check/teacher")
    public CommonResponse<Teacher> checkTeacher(@RequestParam("workId") Integer workId) {
        Teacher teacher = teacherRedisTemplate.opsForValue().get(workId.toString());
        if (teacher != null) {
            return new CommonResponse<Teacher>().ok().data(teacher);
        } else {
            return new CommonResponse<Teacher>().error("认证失败,请重新登录");
        }
    }
}
