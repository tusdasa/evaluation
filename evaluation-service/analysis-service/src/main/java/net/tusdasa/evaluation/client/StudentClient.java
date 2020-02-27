package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.StudentClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "evaluation-student-service", path = "/student", fallback = StudentClientImpl.class)
public interface StudentClient {
    @GetMapping("/{studentId}")
    CommonResponse<Student> getStudentById(@PathVariable(name = "studentId") Long studentId);
}
