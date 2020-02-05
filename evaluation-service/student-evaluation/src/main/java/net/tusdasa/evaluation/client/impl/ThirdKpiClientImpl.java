package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.ThirdKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.stereotype.Component;

@Component
public class ThirdKpiClientImpl implements ThirdKpiClient {
    @Override
    public CommonResponse<ThirdKpi> findAllBySecondKpiIds(IdsRequest idsRequest) {
        return new CommonResponse<ThirdKpi>().error("忙");
    }
}
