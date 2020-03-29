package net.tusdasa.evaluation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "检索用户信息")
public class CheckController {

    private RedisTemplate<String, Student> studentRedisTemplate;

    private RedisTemplate<String, Teacher> teacherRedisTemplate;


    public CheckController(RedisTemplate<String, Student> studentRedisTemplate, RedisTemplate<String, Teacher> teacherRedisTemplate) {
        this.studentRedisTemplate = studentRedisTemplate;
        this.teacherRedisTemplate = teacherRedisTemplate;
    }

    @PostMapping("/check/student")
    @ApiOperation("检查学生信息")
    @ApiImplicitParam(paramType = "query", name = "studentId", value = "学号", dataType = "String")
    public CommonResponse<Student> checkStudent(@RequestParam("studentId") String studentId) {
        Student student = studentRedisTemplate.opsForValue().get(studentId);
        if (student != null) {
            return new CommonResponse<Student>().ok().data(student);
        } else {
            return new CommonResponse<Student>().error("认证失败,请重新登录");
        }
    }

    @ApiOperation(value = "检查教师信息")
    @ApiImplicitParam(paramType = "query", name = "workId", value = "工号", dataType = "String")
    @PostMapping("/check/teacher")
    public CommonResponse<Teacher> checkTeacher(@RequestParam("workId") String workId) {
        Teacher teacher = teacherRedisTemplate.opsForValue().get(workId);
        if (teacher != null) {
            return new CommonResponse<Teacher>().ok().data(teacher);
        } else {
            return new CommonResponse<Teacher>().error("认证失败,请重新登录");
        }
    }
}
