package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.RightClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Right;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "evaluation-authorization-service", path = "/auth", fallback = RightClientImpl.class)
public interface RightClient {

    @GetMapping("/right/{id}")
    CommonResponse<Right> findRightById(@PathVariable("id") Integer id);

    @PostMapping("/check/teacher")
    CommonResponse<Teacher> checkTeacher(@RequestParam("workId") Integer workId);

}
