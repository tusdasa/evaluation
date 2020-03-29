package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.StudentClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.vo.StudentRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-08 6:28 PM
 */

@Service
public class StudentService {

    private StudentClient studentClient;

    public StudentService(StudentClient studentClient) {
        this.studentClient = studentClient;
    }

    public CommonResponse<Student> findById(Long id) {
        return this.studentClient.getStudentById(id);
    }

    public CommonResponse<String> update(StudentRequest request) {
        return this.studentClient.updateStudent(request);
    }

}
