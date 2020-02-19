package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.RightClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Right;
import net.tusdasa.evaluation.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class RightClientImpl implements RightClient {
    @Override
    public CommonResponse<Right> findRightById(Integer id) {
        return new CommonResponse<Right>().error("忙");
    }

    @Override
    public CommonResponse<Teacher> checkTeacher(Integer workId) {
        return new CommonResponse<Teacher>().error("忙");
    }
}
