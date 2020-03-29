package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.ThirdKPIClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "evaluation-thirdkpi-service", path = "/thirdkpi", fallback = ThirdKPIClientImpl.class)
public interface ThirdKPIClient {

    @GetMapping("/")
    CommonResponse<ThirdKpi> findAll();

    @GetMapping("/{thirdKpiId}")
    CommonResponse<ThirdKpi> findThirdKpiById(@PathVariable("thirdKpiId") Integer thirdKpiId);

    @PostMapping("/")
    CommonResponse<String> createThirdKpi(@RequestBody ThirdKpiRequest request);

    @PutMapping("/")
    CommonResponse<String> updateThirdKpi(@RequestBody ThirdKpiRequest request);

    @DeleteMapping("/{thirdKpiId}")
    CommonResponse<String> deleteThirdKpi(@PathVariable("thirdKpiId") Integer thirdKpiId);
}
