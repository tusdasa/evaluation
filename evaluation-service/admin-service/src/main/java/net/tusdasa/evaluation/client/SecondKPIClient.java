package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.SecondKPIClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.vo.SecondKpiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "evaluation-secondkpi-service", path = "/secondkpi", fallback = SecondKPIClientImpl.class)
public interface SecondKPIClient {

    @GetMapping("/")
    CommonResponse<SecondKpi> findAll();

    @GetMapping("/{secondKpiId}")
    CommonResponse<SecondKpi> findById(@PathVariable("secondKpiId") Integer secondKpiId);

    @PostMapping("/")
    CommonResponse<String> createSecondKpi(@RequestBody SecondKpiRequest request);

    @PutMapping("/")
    CommonResponse<String> updateSecondKpi(@RequestBody SecondKpiRequest request);

}
