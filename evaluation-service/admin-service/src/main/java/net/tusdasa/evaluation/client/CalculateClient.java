package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.CalculateClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: tusdasa
 * @Date: 2020-03-24 12:51 PM
 */

@FeignClient(value = "evaluation-calulation-service", path = "/calulation", fallback = CalculateClientImpl.class)
public interface CalculateClient {

    @GetMapping("/{id}")
    CommonResponse<Result> findById(@PathVariable("id") Integer id);
}
