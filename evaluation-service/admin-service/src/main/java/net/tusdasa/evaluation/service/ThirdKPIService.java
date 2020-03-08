package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.ThirdKPIClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-08 4:39 PM
 */

@Service
public class ThirdKPIService {

    private ThirdKPIClient thirdKPIClient;

    public ThirdKPIService(ThirdKPIClient thirdKPIClient) {
        this.thirdKPIClient = thirdKPIClient;
    }

    public CommonResponse<ThirdKpi> findAll() {
        return this.thirdKPIClient.findAll();
    }

    public CommonResponse<ThirdKpi> findById(Integer id) {
        return this.thirdKPIClient.findThirdKpiById(id);
    }


    public CommonResponse<String> update(ThirdKpiRequest request) {
        return this.thirdKPIClient.updateThirdKpi(request);
    }

    public CommonResponse<String> create(ThirdKpiRequest request) {
        return this.thirdKPIClient.createThirdKpi(request);
    }

}
