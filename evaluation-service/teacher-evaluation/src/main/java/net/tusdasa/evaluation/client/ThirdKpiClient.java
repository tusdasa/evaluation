package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.ThirdKpiClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "evaluation-thirdkpi-service", path = "/thirdkpi", fallback = ThirdKpiClientImpl.class)
public interface ThirdKpiClient {
    @PostMapping("/second")
    CommonResponse<ThirdKpi> findAllBySecondKpiIds(@RequestBody IdsRequest idsRequest);
}
