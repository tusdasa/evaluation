package net.tusdasa.evaluation.client.impl;

import net.tusdasa.evaluation.client.SecondKpiClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.stereotype.Component;

@Component
public class SecondKpiClientImpl implements SecondKpiClient {
    @Override
    public CommonResponse<SecondKpi> findAllByFirstKpi(IdsRequest idsRequest) {
        return new CommonResponse<SecondKpi>().error("忙");
    }
}
