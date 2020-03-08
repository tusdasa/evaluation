package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.SecondKPIClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.vo.SecondKpiRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-07 4:22 PM
 */

@Service
public class SecondKPIService {

    private SecondKPIClient secondKPIClient;

    public SecondKPIService(SecondKPIClient secondKPIClient) {
        this.secondKPIClient = secondKPIClient;
    }

    public CommonResponse<SecondKpi> findAll() {
        return this.secondKPIClient.findAll();
    }

    public CommonResponse<SecondKpi> findById(Integer id) {
        return this.secondKPIClient.findById(id);
    }

    public CommonResponse<String> update(SecondKpiRequest request) {
        return this.secondKPIClient.updateSecondKpi(request);
    }

    public CommonResponse<String> create(SecondKpiRequest request) {
        return this.secondKPIClient.createSecondKpi(request);
    }

}
