package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.TeacherClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import net.tusdasa.evaluation.vo.TeacherRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:54 PM
 */

@Component
public class TeacherClientImpl implements TeacherClient {
    @Override
    public CommonResponse<Teacher> getTeacherById(Integer workId) {
        return new CommonResponse<Teacher>().busy();
    }

    @Override
    public CommonResponse<String> updateTeacher(TeacherRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> createTeacher(TeacherRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<Teacher> findAll(Integer page, Integer size) {
        return new CommonResponse<Teacher>().busy();
    }
}
