package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.FirstKPIClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.vo.FirstKpiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "evaluation-firstkpi-service", path = "/firstkpi", fallback = FirstKPIClientImpl.class)
public interface FirstKPIClient {

    @PostMapping("/")
    CommonResponse<String> createFirstKpi(@RequestBody FirstKpiRequest request);

    @PutMapping("/")
    CommonResponse<String> updateFirstKpi(@RequestBody FirstKpiRequest request);

    @GetMapping("/")
    CommonResponse<FirstKpi> findAll();

    @GetMapping("/{firstKpiId}")
    CommonResponse<FirstKpi> findById(@PathVariable("firstKpiId") Integer firstKpiId);

}
