package net.tusdasa.evaluation.controller;

import lombok.extern.slf4j.Slf4j;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-02-24 3:57 PM
 */

@Slf4j
@RestController
public class LogoutController {

    private RedisTemplate<String, Student> studentRedisTemplate;

    private RedisTemplate<String, Teacher> teacherRedisTemplate;

    public LogoutController(RedisTemplate<String, Student> studentRedisTemplate, RedisTemplate<String, Teacher> teacherRedisTemplate) {
        this.studentRedisTemplate = studentRedisTemplate;
        this.teacherRedisTemplate = teacherRedisTemplate;
    }

    @GetMapping("/logout/student")
    public CommonResponse<String> studentLogout(@RequestHeader("studentId") String studentId) {
        studentRedisTemplate.delete(studentId);
        log.info("student logout {}", studentId);
        return new CommonResponse<String>().ok();
    }

    @GetMapping("/logout/teacher")
    public CommonResponse<String> teacherLogout(@RequestParam("workId") String workId) {
        teacherRedisTemplate.delete(workId);
        log.info("teacher logout {}", workId);
        return new CommonResponse<String>().ok();
    }

}
