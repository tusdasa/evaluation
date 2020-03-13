package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.entity.Evidence;
import net.tusdasa.evaluation.entity.Level;
import net.tusdasa.evaluation.service.CalculationService;
import net.tusdasa.evaluation.vo.CalculationRuleRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tusdasa
 * @Date: 2020-03-09 4:37 PM
 */

@RestController
@RequestMapping("/rule")
public class CalculationController {

    private CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/calculation/{id}")
    public CommonResponse<CalculationRule> findCalculationRuleById(@PathVariable("id") Integer id) {
        return this.calculationService.findCalculationRuleById(id);
    }

    @GetMapping("/calculation")
    public CommonResponse<CalculationRule> findAllCalculationRule() {
        return this.calculationService.findAllCalculationRule();
    }

    @PutMapping("/calculation")
    public CommonResponse<String> updateCalculationRule(CalculationRuleRequest ruleRequest) {
        return this.calculationService.updateCalculationRule(ruleRequest);
    }

    @PostMapping("/calculation")
    public CommonResponse<String> createCalculationRule(CalculationRuleRequest ruleRequest) {
        return this.calculationService.createCalculationRule(ruleRequest);
    }

    @GetMapping("/evidence")
    public CommonResponse<Evidence> findAllEvidence() {
        return this.calculationService.findAllEvidence();
    }

    @GetMapping("/level")
    public CommonResponse<Level> findAllLevel() {
        return this.calculationService.findAllLevel();
    }

    @GetMapping("/level/{id}")
    public CommonResponse<Level> findEvidenceById(@PathVariable("id") Integer id) {
        return this.calculationService.findEvidenceById(id);
    }

    @PutMapping("/level")
    public CommonResponse<String> updateEvidence(Evidence evidence) {
        return this.calculationService.updateEvidence(evidence);
    }

    @PostMapping("/level")
    public CommonResponse<String> createEvidence(Evidence evidence) {
        return this.calculationService.createEvidence(evidence);
    }

    @DeleteMapping("/evidence/{id}")
    public CommonResponse<String> deleteEvidence(@PathVariable("id") Integer id) {
        return this.calculationService.deleteEvidence(id);
    }

    @DeleteMapping("/level/{id}")
    public CommonResponse<String> deleteLevel(@PathVariable("id") Integer id) {
        return this.calculationService.deleteLevel(id);
    }

    @DeleteMapping("/calculation/{id}")
    public CommonResponse<String> deleteCalculationRule(@PathVariable("id") Integer id) {
        return this.calculationService.deleteCalculationRule(id);
    }

}
