package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.TeacherClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-02-23 1:33 PM
 */

@Component
public class TeacherClientImpl implements TeacherClient {

    @Override
    public CommonResponse<Teacher> getTeacherById(Integer workId) {
        return new CommonResponse<Teacher>().busy();
    }
}
