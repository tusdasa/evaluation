package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.GradeClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Grade;
import net.tusdasa.evaluation.vo.GradeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "evaluation-grade-service", path = "/grade", fallback = GradeClientImpl.class)
public interface GradeClient {

    @GetMapping("/")
    CommonResponse<Grade> findAll();

    @PostMapping("/")
    CommonResponse<String> create(@RequestBody GradeRequest request);

    @PutMapping("/")
    CommonResponse<String> update(@RequestBody GradeRequest request);

    @GetMapping("/{gradeId}")
    CommonResponse<Grade> findById(@PathVariable("gradeId") Integer gradeId);
}
