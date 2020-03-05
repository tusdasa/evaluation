package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.SecondKPIClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.vo.SecondKpiRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:54 PM
 */

@Component
public class SecondKPIClientImpl implements SecondKPIClient {
    @Override
    public CommonResponse<SecondKpi> findAll() {
        return new CommonResponse<SecondKpi>().busy();
    }

    @Override
    public CommonResponse<SecondKpi> findById(Integer secondKpiId) {
        return new CommonResponse<SecondKpi>().busy();
    }

    @Override
    public CommonResponse<String> createSecondKpi(SecondKpiRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> updateSecondKpi(SecondKpiRequest request) {
        return new CommonResponse<String>().busy();
    }
}
