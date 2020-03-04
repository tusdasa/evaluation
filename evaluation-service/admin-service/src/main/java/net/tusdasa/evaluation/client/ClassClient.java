package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.vo.StudentClassRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "evaluation-class-service", path = "/class", fallback = ClassClient.class)
public interface ClassClient {

    @GetMapping("/")
    CommonResponse<StudentClass> findAllStudentClass();

    @GetMapping("/{studentClassId}")
    CommonResponse<StudentClass> findByStudentClassId(@PathVariable("studentClassId") Integer studentClassId);

    @PostMapping("/")
    CommonResponse<String> createStudentClass(@RequestBody StudentClassRequest request);

    @PutMapping("/")
    CommonResponse<String> updateStudentClass(@RequestBody StudentClassRequest request);

}
