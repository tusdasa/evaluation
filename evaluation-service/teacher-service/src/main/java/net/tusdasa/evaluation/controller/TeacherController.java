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

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/{workId}")
    public CommonResponse<Teacher> getTeacherById(@PathVariable(name = "workId") Integer workId) {
        Teacher teacher = teacherService.findTeacherByWorldId(workId);
        if (teacher != null) {
            return new CommonResponse<Teacher>().ok().data(teacher);
        } else {
            return new CommonResponse<Teacher>().error("不存在");
        }
    }

    @PostMapping("/auth")
    public CommonResponse<Teacher> getTeacher(@RequestParam("workId") Integer workId, @RequestParam("password") String password) {
        Map<String, Object> map = teacherService.findTeacherByPassword(workId, password);
        if (map.get("code").equals(1)) {
            return new CommonResponse<Teacher>().ok().data((Teacher) map.get("obj"));
        } else {
            return new CommonResponse<Teacher>().error(map.get("msg").toString());
        }
    }

    /**
     * {
     * "workId": 1810212129,
     * "teacherSecret": "1234456",
     * "roleId": 1,
     * "departmentId": 9,
     * "teacherName": "爸爸",
     * "professionalId": 1,
     * "stateId": 1
     * }
     */

    @PutMapping("/")
    public CommonResponse<String> updateTeacher(@RequestBody TeacherRequest request) {
        if (request.isUpdateRequest()) {
            teacherService.updateTeacher(request);
            return new CommonResponse<String>().ok();
        } else {
            return new CommonResponse<String>().error();
        }
    }

    /**
     * {
     * "workId": 1810212129,
     * "teacherSecret": "1234456",
     * "roleId": 1,
     * "departmentId": 9,
     * "teacherName": "爸爸",
     * "professionalId": 1,
     * "stateId": 1
     * }
     */
    @PostMapping("/")
    public CommonResponse<String> createTeacher(@RequestBody TeacherRequest request) {
        if (request.isCreateRequest()) {
            teacherService.addTeacher(request);
            return new CommonResponse<String>().ok();
        }
        return new CommonResponse<String>().error();
    }

    @GetMapping("/department/{departmentId}/role/{roleId}/state/{stateId}")
    public CommonResponse<Teacher> findTeacher(@PathVariable("departmentId") Integer departmentId,
                                               @PathVariable("roleId") Integer roleId,
                                               @PathVariable("stateId") Integer stateId) {
        return new CommonResponse<Teacher>().ok().table(this.teacherService.findAllTeacherByRoleAndDepartment(departmentId, stateId, roleId));
    }

    @GetMapping("/")
    public CommonResponse<Teacher> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                           @RequestParam(name = "size", defaultValue = "20", required = false) Integer size) {
        return new CommonResponse<Teacher>().ok().table(this.teacherService.findAll(page, size));
    }

}
