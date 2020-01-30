package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "evaluation-student-service",path = "/student",fallback = StudentAuthClientImpl.class)
public interface StudentAuthClient {
    @GetMapping("/{id}/{password}")
    CommonResponse<Student> getStudent(@PathVariable(name = "id") Long id, @PathVariable("password") String password);
}
