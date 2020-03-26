package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.CalculateClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYearResult;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-24 12:56 PM
 */

@Component
public class CalculateClientImpl implements CalculateClient {
    @Override
    public CommonResponse<AcademicYearResult> findById(Integer id) {
        return new CommonResponse<AcademicYearResult>().busy();
    }
}
