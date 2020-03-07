package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.FirstKPIClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.vo.FirstKpiRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: tusdasa
 * @Date: 2020-03-07 1:22 PM
 */

@Service
public class FirstKpiService {

    private FirstKPIClient firstKPIClient;

    public FirstKpiService(FirstKPIClient firstKPIClient) {
        this.firstKPIClient = firstKPIClient;
    }

    public CommonResponse<FirstKpi> findAll() {
        return this.firstKPIClient.findAll();
    }

    public CommonResponse<FirstKpi> findById(Integer id) {
        return this.firstKPIClient.findById(id);
    }

    public CommonResponse<String> create(@RequestBody FirstKpiRequest request) {
        return this.firstKPIClient.createFirstKpi(request);
    }

    public CommonResponse<String> update(@RequestBody FirstKpiRequest request) {
        return this.firstKPIClient.updateFirstKpi(request);
    }
}
