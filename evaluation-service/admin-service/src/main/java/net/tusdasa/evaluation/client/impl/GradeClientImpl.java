package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.GradeClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.vo.GradeRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:54 PM
 */

@Component
public class GradeClientImpl implements GradeClient {

    @Override
    public CommonResponse<Grade> findAll() {
        return new CommonResponse<Grade>().busy();
    }

    @Override
    public CommonResponse<String> create(GradeRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> update(GradeRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<Grade> findById(Integer gradeId) {
        return new CommonResponse<Grade>().busy();
    }
}
