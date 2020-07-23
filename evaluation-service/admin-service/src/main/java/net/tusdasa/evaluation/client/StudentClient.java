package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.StudentClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.vo.StudentClassRequest;
import net.tusdasa.evaluation.vo.StudentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:27 PM
 */
@FeignClient(value = "evaluation-student-service", path = "/student", fallback = StudentClientImpl.class)
public interface StudentClient {

    @GetMapping("/{studentId}")
    CommonResponse<Student> getStudentById(@PathVariable(name = "studentId") Long studentId);

    @GetMapping("/")
    CommonResponse<Student> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "20", required = false) Integer size);

    @PutMapping("/")
    CommonResponse<String> updateStudent(@RequestBody StudentRequest request);

    @PostMapping("/")
    CommonResponse<String> crateStudent(@RequestBody StudentRequest request);

    /** class **/

    @GetMapping("/class")
    CommonResponse<StudentClass> findAllStudentClass();

    @GetMapping("/class//{studentClassId}")
    CommonResponse<StudentClass> findByStudentClassId(@PathVariable("studentClassId") Integer studentClassId);

    @PostMapping("/class")
    CommonResponse<String> createStudentClass(@RequestBody StudentClassRequest request);

    @PutMapping("/class")
    CommonResponse<String> updateStudentClass(@RequestBody StudentClassRequest request);
}
