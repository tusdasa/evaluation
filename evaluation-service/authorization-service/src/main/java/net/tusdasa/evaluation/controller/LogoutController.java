package net.tusdasa.evaluation.controller;

import io.swagger.annotations.Api;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-02-24 3:57 PM
 */

@RestController
@Api("退出用户")
public class LogoutController {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutController.class);

    private RedisTemplate<String, Student> studentRedisTemplate;

    private RedisTemplate<String, Teacher> teacherRedisTemplate;

    public LogoutController(RedisTemplate<String, Student> studentRedisTemplate, RedisTemplate<String, Teacher> teacherRedisTemplate) {
        this.studentRedisTemplate = studentRedisTemplate;
        this.teacherRedisTemplate = teacherRedisTemplate;
    }

    @GetMapping("/logout/student")
    public CommonResponse<String> studentLogout(@RequestHeader("studentId") String studentId) {
        studentRedisTemplate.delete(studentId);
        LOG.info("student logout {}", studentId);
        return new CommonResponse<String>().ok();
    }

    @GetMapping("/logout/teacher")
    public CommonResponse<String> teacherLogout(@RequestHeader("workId") String workId) {
        teacherRedisTemplate.delete(workId);
        LOG.info("teacher logout {}", workId);
        return new CommonResponse<String>().ok();
    }

}
