package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.StudentClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentClientImpl implements StudentClient {
    @Override
    public CommonResponse<Student> getStudentById(Long studentId) {
        return new CommonResponse<Student>().busy();
    }
}
