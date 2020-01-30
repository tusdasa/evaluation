package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.service.TeacherService;
import net.tusdasa.evaluation.vo.TeacherRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }


    @GetMapping("/{id}")
    public CommonResponse<Teacher> getTeacherById(@PathVariable(name = "id") Integer id){
        Teacher teacher = teacherService.findTeacherByWorldId(id);
        if (teacher!=null){
            return new CommonResponse<Teacher>().ok().data(teacher);
        }else {
            return new CommonResponse<Teacher>().error("不存在");
        }
    }

    @GetMapping("/{id}/{password}")
    public CommonResponse<Teacher> getTeacher(@PathVariable(name = "id") Integer id, @PathVariable("password") String password){
        Map<String,Object> map = teacherService.findTeacherByPassword(id,password);
        if (map.get("code").equals(1)){
            return new CommonResponse<Teacher>().ok().data((Teacher) map.get("obj"));
        }else {
            return new CommonResponse<Teacher>().error(map.get("msg").toString());
        }
    }

    /**
     * {
     *     "workId": 1810212129,
     *     "teacherSecret": "1234456",
     *     "roleId": 1,
     *     "departmentId": 9,
     *     "teacherName": "爸爸",
     *     "professionalId": 1,
     *     "stateId": 1
     * }
     *
     * */

    @PutMapping("/")
    public CommonResponse<String> updateTeacher(@RequestBody TeacherRequest request){
        if (request.isUpdateRequest()){
            teacherService.updateTeacher(request);
            return new CommonResponse<String>().ok();
        }else {
            return new CommonResponse<String>().error();
        }
    }

    /**
     * {
     *     "workId": 1810212129,
     *     "teacherSecret": "1234456",
     *     "roleId": 1,
     *     "departmentId": 9,
     *     "teacherName": "爸爸",
     *     "professionalId": 1,
     *     "stateId": 1
     * }
     *
     * */
    @PostMapping("/")
    public CommonResponse<String> createTeacher(@RequestBody TeacherRequest request){
        if (request.isCreateRequest()){
            teacherService.addTeacher(request);
            return new CommonResponse<String>().ok();
        }else {
            return new CommonResponse<String>().error();
        }
    }


}
