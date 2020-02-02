package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.ThirdKpiClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "evaluation-thirdkpi-service", path = "/thirdkpi", fallback = ThirdKpiClientImpl.class)
public interface ThirdKpiClient {
    @GetMapping("/second/{secondKpiId}")
    CommonResponse<ThirdKpi> findSecondKpiId(@PathVariable("secondKpiId") Integer secondKpiId);
}
