package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.StudentEvaluationClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "evaluation-studentevaluation-service", path = "/sevaluation", fallback = StudentEvaluationClientImpl.class)
public interface StudentEvaluationClient {

    @GetMapping("/result/{termId}")
    CommonResponse<Long> countAllByTermId(@PathVariable("termId") Integer termId);

    @DeleteMapping("/result/{termId}")
    CommonResponse<String> deleteAllByTermId(@PathVariable("termId") Integer termId);

    @GetMapping("/result/{departmentId}/{termId}")
    CommonResponse<Long> countAllByTermIdAndAndDepartmentId(@PathVariable("termId") Integer termId, @PathVariable("departmentId") Integer departmentId);
}
