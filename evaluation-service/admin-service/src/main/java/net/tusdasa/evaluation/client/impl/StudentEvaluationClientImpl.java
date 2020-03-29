package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.StudentEvaluationClient;
import net.tusdasa.evaluation.commons.CommonResponse;

/**
 * @Author: tusdasa
 * @Date: 2020-03-29 1:25 PM
 */

public class StudentEvaluationClientImpl implements StudentEvaluationClient {
    @Override
    public CommonResponse<Long> countAllByTermId(Integer termId) {
        return new CommonResponse<Long>().busy();
    }

    @Override
    public CommonResponse<String> deleteAllByTermId(Integer termId) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<Long> countAllByTermIdAndAndDepartmentId(Integer termId, Integer departmentId) {
        return new CommonResponse<Long>().busy();
    }
}
