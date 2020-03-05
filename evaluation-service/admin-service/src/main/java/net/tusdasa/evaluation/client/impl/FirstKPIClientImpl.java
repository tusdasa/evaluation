package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.FirstKPIClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.vo.FirstKpiRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:53 PM
 */

@Component
public class FirstKPIClientImpl implements FirstKPIClient {
    @Override
    public CommonResponse<String> createFirstKpi(FirstKpiRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> updateFirstKpi(FirstKpiRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<FirstKpi> findAll() {
        return new CommonResponse<FirstKpi>().busy();
    }

    @Override
    public CommonResponse<FirstKpi> findById(Integer firstKpiId) {
        return new CommonResponse<FirstKpi>().busy();
    }
}
