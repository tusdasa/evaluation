package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.FirstKpiClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.FirstKpi;
import net.tusdasa.evaluation.vo.IdsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "evaluation-firstkpi-service", path = "/firstkpi", fallback = FirstKpiClientImpl.class)
public interface FirstKpiClient {
    // 第一学年 第5个
    @PostMapping("/ids/{yearId}")
    CommonResponse<FirstKpi> findAllByAcademicYearAndIds(@PathVariable("yearId") Integer yearId, @RequestBody IdsRequest idsRequest);
}
