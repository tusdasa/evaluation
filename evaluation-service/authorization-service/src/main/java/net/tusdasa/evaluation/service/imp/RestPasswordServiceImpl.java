package net.tusdasa.evaluation.service.imp;

import net.tusdasa.evaluation.client.StudentAuthClient;
import net.tusdasa.evaluation.client.TeacherAuthClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.service.RestPasswordService;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-24 4:52 PM
 */

@Service
public class RestPasswordServiceImpl implements RestPasswordService {

    private StudentAuthClient studentAuthClient;

    private TeacherAuthClient teacherAuthClient;

    public RestPasswordServiceImpl(StudentAuthClient studentAuthClient, TeacherAuthClient teacherAuthClient) {
        this.studentAuthClient = studentAuthClient;
        this.teacherAuthClient = teacherAuthClient;
    }

    @Override
    public CommonResponse<String> restTeacherPassword(Integer workId, String newPassword, String oldPassword) {
        return teacherAuthClient.restPassword(workId, newPassword, oldPassword);
    }

    @Override
    public CommonResponse<String> restStudentPassword(Long studentId, String newPassword, String oldPassword) {
        return studentAuthClient.restPassword(studentId, newPassword, oldPassword);
    }
}
