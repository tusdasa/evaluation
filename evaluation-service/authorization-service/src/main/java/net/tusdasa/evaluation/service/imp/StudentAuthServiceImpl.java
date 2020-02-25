package net.tusdasa.evaluation.service.imp;

import net.tusdasa.evaluation.client.StudentAuthClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Student;
import net.tusdasa.evaluation.service.StudentAuthService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentAuthServiceImpl implements StudentAuthService {

    private StudentAuthClient studentAuthClient;

    public StudentAuthServiceImpl(StudentAuthClient studentAuthClient) {
        this.studentAuthClient = studentAuthClient;
    }

    @Override
    public Map<String, Object> findStudent(Long studentId, String password) {
        HashMap<String, Object> result = new HashMap<>();
        CommonResponse<Student> response = studentAuthClient.getStudent(studentId, DigestUtils.md5DigestAsHex(password.getBytes()));

        if (response.success() && response.getCode() == 200) {
            // 认证成功
            result.put("code", response.getCode());
            result.put("obj", response.getData());
        } else {
            // 认证失败
            result.put("code", response.getCode());
            result.put("msg", response.getMessage());
        }

        return result;
    }
}
