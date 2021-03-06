package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.TeacherClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: tusdasa
 * @Date: 2020-02-23 1:20 PM
 */

@FeignClient(value = "evaluation-teacher-service", path = "/teacher", fallback = TeacherClientImpl.class)
public interface TeacherClient {
    @GetMapping("/{workId}")
    CommonResponse<Teacher> getTeacherById(@PathVariable(name = "workId") Integer workId);
}
