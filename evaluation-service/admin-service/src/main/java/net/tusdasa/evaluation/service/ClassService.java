package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.ClassClient;
import net.tusdasa.evaluation.client.StudentClient;
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

    private StudentClient studentClient;

    public ClassService(StudentClient studentClient) {
        this.studentClient = studentClient;
    }

    public CommonResponse<StudentClass> findAllStudentClass() {
        return this.studentClient.findAllStudentClass();
    }

    public CommonResponse<StudentClass> findByStudentClassId(Integer id) {
        return this.studentClient.findByStudentClassId(id);
    }

    public CommonResponse<String> createStudentClass(StudentClassRequest request) {
        return this.studentClient.createStudentClass(request);
    }

    public CommonResponse<String> updateStudentClass(StudentClassRequest request) {
        return this.studentClient.updateStudentClass(request);
    }

}
