package net.tusdasa.evaluation.controller;

import io.swagger.annotations.Api;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.commons.UserInfo;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-02-24 1:48 PM
 */

@RestController
@Api("用户信息获取")
public class UserInfoController {

    private RedisTemplate<String, Student> studentRedisTemplate;

    private RedisTemplate<String, Teacher> teacherRedisTemplate;

    public UserInfoController(RedisTemplate<String, Student> studentRedisTemplate, RedisTemplate<String, Teacher> teacherRedisTemplate) {
        this.studentRedisTemplate = studentRedisTemplate;
        this.teacherRedisTemplate = teacherRedisTemplate;
    }

    @GetMapping("/studentinfo")
    public CommonResponse<UserInfo> getStudentInfo(@RequestHeader("studentId") String studentId) {
        Student student = studentRedisTemplate.opsForValue().get(studentId.toString());
        if (student != null) {
            UserInfo userInfo = UserInfo.builder().build().withId(studentId).withName(student.getStudentName()).withRole(1);
            return new CommonResponse<UserInfo>().ok().data(userInfo);
        }
        return new CommonResponse<UserInfo>().error("未找到用户信息");
    }

    @GetMapping("/teacherinfo")
    public CommonResponse<UserInfo> getTeacherInfo(@RequestHeader("workId") String workId) {
        System.out.println(workId);
        Teacher teacher = teacherRedisTemplate.opsForValue().get(workId);
        if (teacher != null) {
            UserInfo userInfo = UserInfo.builder().build().withId(workId).withName(teacher.getTeacherName()).withRole(teacher.getRoleId());
            return new CommonResponse<UserInfo>().ok().data(userInfo);
        }
        return new CommonResponse<UserInfo>().error("未找到用户信息");
    }

}
