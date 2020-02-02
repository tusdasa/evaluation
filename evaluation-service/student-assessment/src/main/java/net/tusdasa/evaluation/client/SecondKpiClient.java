package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.SecondKpiClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.SecondKpi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "evaluation-secondkpi-service", path = "/secondkpi", fallback = SecondKpiClientImpl.class)
public interface SecondKpiClient {
    @GetMapping("/first/{firstId}")
    CommonResponse<SecondKpi> findByFirstId(@PathVariable("firstId") Integer firstId);
}
