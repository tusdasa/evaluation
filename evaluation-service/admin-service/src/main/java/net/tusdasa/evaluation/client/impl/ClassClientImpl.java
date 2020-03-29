package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.ClassClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.vo.StudentClassRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:53 PM
 */

@Component
public class ClassClientImpl implements ClassClient {
    @Override
    public CommonResponse<StudentClass> findAllStudentClass() {
        return new CommonResponse<StudentClass>().busy();
    }

    @Override
    public CommonResponse<StudentClass> findByStudentClassId(Integer studentClassId) {
        return new CommonResponse<StudentClass>().busy();
    }

    @Override
    public CommonResponse<String> createStudentClass(StudentClassRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> updateStudentClass(StudentClassRequest request) {
        return new CommonResponse<String>().busy();
    }
}
