package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:27 PM
 */

public interface StudentClient {

    @GetMapping("/{studentId}")
    CommonResponse<Student> getStudentById(@PathVariable(name = "studentId") Long studentId);

    @GetMapping("/")
    CommonResponse<Student> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "20", required = false) Integer size);


}
