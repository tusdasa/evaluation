package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.TeacherClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.vo.TeacherRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "evaluation-teacher-service", path = "/teacher", fallback = TeacherClientImpl.class)
public interface TeacherClient {

    @GetMapping("/{workId}")
    CommonResponse<Teacher> getTeacherById(@PathVariable(name = "workId") Integer workId);

    @PutMapping("/")
    CommonResponse<String> updateTeacher(@RequestBody TeacherRequest request);

    @PostMapping("/")
    CommonResponse<String> createTeacher(@RequestBody TeacherRequest request);

    @GetMapping("/")
    CommonResponse<Teacher> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "20", required = false) Integer size);

    @GetMapping("/department/{departmentId}/role/{roleId}/state/{stateId}/page")
    CommonResponse<Teacher> findTeacherByPage(@PathVariable("departmentId") Integer departmentId,
                                              @PathVariable("roleId") Integer roleId,
                                              @PathVariable("stateId") Integer stateId,
                                              @RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size
    );

}
