package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.StudentClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:54 PM
 */

@Component
public class StudentClientImpl implements StudentClient {
    @Override
    public CommonResponse<Student> getStudentById(Long studentId) {
        return new CommonResponse<Student>().busy();
    }

    @Override
    public CommonResponse<Student> findAll(Integer page, Integer size) {
        return new CommonResponse<Student>().busy();
    }
}
