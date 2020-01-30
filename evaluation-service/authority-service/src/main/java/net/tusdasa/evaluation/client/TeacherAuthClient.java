package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "evaluation-teacher-service", path = "/teacher" ,fallback = TeacherAuthClientImpl.class)
public interface TeacherAuthClient {
    @GetMapping("/{id}/{password}")
    CommonResponse<Teacher> getTeacher(@PathVariable(name = "id") Integer id, @PathVariable("password") String password);
}
