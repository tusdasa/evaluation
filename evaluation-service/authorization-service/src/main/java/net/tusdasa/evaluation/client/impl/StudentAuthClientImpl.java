package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.StudentAuthClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentAuthClientImpl implements StudentAuthClient {
    @Override
    public CommonResponse<Student> getStudent(Long studentId, String password) {
        return new CommonResponse<Student>().busy();
    }
}
