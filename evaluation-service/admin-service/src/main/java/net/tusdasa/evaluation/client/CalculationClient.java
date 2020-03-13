package net.tusdasa.evaluation.client;

import net.tusdasa.evaluation.client.impl.CalculationClientImpl;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.entity.Evidence;
import net.tusdasa.evaluation.entity.Level;
import net.tusdasa.evaluation.vo.CalculationRuleRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-01 7:25 PM
 */

@FeignClient(value = "evaluation-calculationrule-service", path = "/calc", fallback = CalculationClientImpl.class)
public interface CalculationClient {

    @GetMapping("/{id}")
    CommonResponse<CalculationRule> findCalculationRuleById(@PathVariable("id") Integer id);

    @GetMapping("/")
    CommonResponse<CalculationRule> findAllCalculationRule();

    @PutMapping("/")
    CommonResponse<String> updateCalculationRule(@RequestBody CalculationRuleRequest ruleRequest);

    @PostMapping("/")
    CommonResponse<String> createCalculationRule(@RequestBody CalculationRuleRequest ruleRequest);

    @DeleteMapping("/{id}")
    CommonResponse<String> deleteCalculationRule(@PathVariable("id") Integer id);

    @GetMapping("/evidence/{id}")
    CommonResponse<Evidence> findEvidenceById(@PathVariable("id") Integer id);

    @GetMapping("/evidence/")
    CommonResponse<Evidence> findAllEvidence();

    @PostMapping("/evidence/")
    CommonResponse<String> createEvidence(@RequestBody Evidence evidence);

    @PutMapping("/evidence/")
    CommonResponse<String> updateEvidence(@RequestBody Evidence evidence);

    @DeleteMapping("/evidence/{id}")
    CommonResponse<String> deleteEvidence(@PathVariable("id") Integer id);

    @GetMapping("/level/")
    CommonResponse<Level> findAllLevel();

    @GetMapping("/level/{id}")
    CommonResponse<Level> findLevelById(@PathVariable("id") Integer id);

    @PutMapping("/level/")
    CommonResponse<String> updateLevel(@RequestBody Level level);

    @PostMapping("/level/")
    CommonResponse<String> createLevel(@RequestBody Level level);

    @DeleteMapping("/level/{id}")
    CommonResponse<String> deleteLevel(@PathVariable("id") Integer id);

}
