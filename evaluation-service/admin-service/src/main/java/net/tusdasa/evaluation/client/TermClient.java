package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.TermClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Term;
import net.tusdasa.evaluation.vo.TermRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:50 PM
 */

@FeignClient(value = "evaluation-term-service", path = "/term", fallback = TermClientImpl.class)
public interface TermClient {

    @GetMapping("/")
    CommonResponse<Term> findAllTerm();

    @GetMapping("/{termId}")
    CommonResponse<Term> findById(@PathVariable("termId") Integer termId);

    @PostMapping("/")
    CommonResponse<String> createTerm(@RequestBody TermRequest request);

    @PutMapping("/")
    CommonResponse<String> updateTerm(@RequestBody TermRequest request);
}
