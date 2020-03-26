package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.CalculateClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.AcademicYearResult;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-24 12:58 PM
 */

@Service
public class CalculateService {

    private CalculateClient calculateClient;

    public CalculateService(CalculateClient calculateClient) {
        this.calculateClient = calculateClient;
    }

    public CommonResponse<AcademicYearResult> findById(Integer id) {
        return calculateClient.findById(id);
    }
}
