package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.FirstKpiClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FirstKpi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "evaluation-firstkpi-service", path = "/firstkpi", fallback = FirstKpiClientImpl.class)
public interface FirstKpiClient {
    @GetMapping("/year/{yearId}/{firstKpiId}")
    CommonResponse<FirstKpi> findAllByFirstKpiByAcademiceyearAndId(@PathVariable("yearId") Integer yearId, @PathVariable("firstKpiId") Integer firstKpiId);
}
