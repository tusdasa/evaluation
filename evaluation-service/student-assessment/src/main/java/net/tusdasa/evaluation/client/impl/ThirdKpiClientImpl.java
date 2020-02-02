package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.ThirdKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import org.springframework.stereotype.Component;

@Component
public class ThirdKpiClientImpl implements ThirdKpiClient {
    @Override
    public CommonResponse<ThirdKpi> findSecondKpiId(Integer secondKpiId) {
        return new CommonResponse<ThirdKpi>().error("忙");
    }
}
