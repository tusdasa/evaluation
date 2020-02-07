package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.TeacherAuthClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "evaluation-teacher-service", path = "/teacher", fallback = TeacherAuthClientImpl.class)
public interface TeacherAuthClient {
    @PostMapping("/auth")
    CommonResponse<Teacher> getTeacher(@RequestParam("workId") Integer workId, @RequestParam("password") String password);
}
