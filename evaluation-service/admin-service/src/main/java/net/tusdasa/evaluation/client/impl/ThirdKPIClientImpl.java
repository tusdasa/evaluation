package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.ThirdKPIClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;
import org.springframework.stereotype.Component;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:55 PM
 */

@Component
public class ThirdKPIClientImpl implements ThirdKPIClient {
    @Override
    public CommonResponse<ThirdKpi> findAll() {
        return new CommonResponse<ThirdKpi>().busy();
    }

    @Override
    public CommonResponse<ThirdKpi> findThirdKpiById(Integer thirdKpiId) {
        return new CommonResponse<ThirdKpi>().busy();
    }

    @Override
    public CommonResponse<String> createThirdKpi(ThirdKpiRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> updateThirdKpi(ThirdKpiRequest request) {
        return new CommonResponse<String>().busy();
    }

    @Override
    public CommonResponse<String> deleteThirdKpi(Integer thirdKpiId) {
        return new CommonResponse<String>().busy();
    }
}
