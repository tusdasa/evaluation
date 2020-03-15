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
    public CommonResponse<String> updateCalculationRule(@RequestBody CalculationRuleRequest ruleRequest) {
        return this.calculationService.updateCalculationRule(ruleRequest);
    }

    @PostMapping("/calculation")
    public CommonResponse<String> createCalculationRule(@RequestBody CalculationRuleRequest ruleRequest) {
        return this.calculationService.createCalculationRule(ruleRequest);
    }

    @DeleteMapping("/calculation/{id}")
    public CommonResponse<String> deleteCalculationRule(@PathVariable("id") Integer id) {
        return this.calculationService.deleteCalculationRule(id);
    }

    @GetMapping("/evidence")
    public CommonResponse<Evidence> findAllEvidence() {
        return this.calculationService.findAllEvidence();
    }

    @DeleteMapping("/evidence/{id}")
    public CommonResponse<String> deleteEvidence(@PathVariable("id") Integer id) {
        return this.calculationService.deleteEvidence(id);
    }

    @GetMapping("/evidence/{id}")
    public CommonResponse<Evidence> findEvidenceById(@PathVariable("id") Integer id) {
        return this.calculationService.findEvidenceById(id);
    }

    @PutMapping("/evidence")
    public CommonResponse<String> updateEvidence(@RequestBody Evidence evidence) {
        return this.calculationService.updateEvidence(evidence);
    }

    @PostMapping("/evidence")
    public CommonResponse<String> createEvidence(@RequestBody Evidence evidence) {
        return this.calculationService.createEvidence(evidence);
    }

    @GetMapping("/level")
    public CommonResponse<Level> findAllLevel() {
        return this.calculationService.findAllLevel();
    }

    @DeleteMapping("/level/{id}")
    public CommonResponse<String> deleteLevel(@PathVariable("id") Integer id) {
        return this.calculationService.deleteLevel(id);
    }

    @GetMapping("/level/{id}")
    public CommonResponse<Level> findLevelById(@PathVariable("id") Integer id) {
        return this.calculationService.findLevelById(id);
    }

    @PutMapping("/level")
    public CommonResponse<String> updateLevel(@RequestBody Level level) {
        return this.calculationService.updateLevel(level);
    }

    @PostMapping("/level")
    public CommonResponse<String> createLevel(@RequestBody Level level) {
        return this.calculationService.createLevel(level);
    }


}
