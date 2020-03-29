package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.TeacherEvaluationClient;
import net.tusdasa.evaluation.commons.CommonResponse;

/**
 * @Author: tusdasa
 * @Date: 2020-03-29 1:21 PM
 */

public class TeacherEvaluationClientImpl implements TeacherEvaluationClient {
    @Override
    public CommonResponse<String> deleteTeacherResult(Integer termId) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<Long> countAll(Integer termId) {
        return new CommonResponse<Long>().busy();
    }
}
