package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.SecondKpiClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "evaluation-secondkpi-service", path = "/secondkpi", fallback = SecondKpiClientImpl.class)
public interface SecondKpiClient {
    @PostMapping("/first")
    CommonResponse<SecondKpi> findAllByFirstKpiIds(@RequestBody IdsRequest idsRequest);
}
