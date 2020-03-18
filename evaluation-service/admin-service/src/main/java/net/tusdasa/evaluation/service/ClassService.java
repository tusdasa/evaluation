package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.ClassClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.StudentClass;
import net.tusdasa.evaluation.vo.StudentClassRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-16 4:18 PM
 */

@Service
public class ClassService {

    private ClassClient classClient;

    public ClassService(ClassClient classClient) {
        this.classClient = classClient;
    }

    public CommonResponse<StudentClass> findAllStudentClass() {
        return this.classClient.findAllStudentClass();
    }

    public CommonResponse<StudentClass> findByStudentClassId(Integer id) {
        return this.classClient.findByStudentClassId(id);
    }

    public CommonResponse<String> createStudentClass(StudentClassRequest request) {
        return this.classClient.createStudentClass(request);
    }

    public CommonResponse<String> updateStudentClass(StudentClassRequest request) {
        return this.classClient.updateStudentClass(request);
    }

}
