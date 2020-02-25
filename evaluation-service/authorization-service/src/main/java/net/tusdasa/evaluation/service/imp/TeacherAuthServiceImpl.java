package net.tusdasa.evaluation.service.imp;

import net.tusdasa.evaluation.client.TeacherAuthClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.service.TeacherAuthService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherAuthServiceImpl implements TeacherAuthService {

    private TeacherAuthClient teacherAuthClient;

    public TeacherAuthServiceImpl(TeacherAuthClient teacherAuthClient) {
        this.teacherAuthClient = teacherAuthClient;
    }

    @Override
    public Map<String, Object> findTeacher(Integer workId, String password) {
        HashMap<String, Object> result = new HashMap<>();
        CommonResponse<Teacher> response = teacherAuthClient.getTeacher(workId, DigestUtils.md5DigestAsHex(password.getBytes()));

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
