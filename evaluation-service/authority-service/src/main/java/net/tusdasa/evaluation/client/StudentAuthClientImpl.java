package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentAuthClientImpl implements StudentAuthClient {
    @Override
    public CommonResponse<Student> getStudent(Long id, String password) {
        return new CommonResponse<Student>().error("忙");
    }
}
