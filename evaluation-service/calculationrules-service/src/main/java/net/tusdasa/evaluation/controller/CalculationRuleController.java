package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.service.CalculationRuleService;
import net.tusdasa.evaluation.vo.CalculationRuleRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculationRuleController {

    private CalculationRuleService calculationRuleService;

    public CalculationRuleController(CalculationRuleService calculationRuleService) {
        this.calculationRuleService = calculationRuleService;
    }

    @GetMapping("/{id}")
    public CommonResponse<CalculationRule> findCalculationRuleById(@PathVariable("id") Integer id) {
        CalculationRule calculationRule = calculationRuleService.findCalculationRuleById(id);
        if (calculationRule != null) {
            return new CommonResponse<CalculationRule>().ok().data(calculationRule);
        } else {
            return new CommonResponse<CalculationRule>().error("未找到");
        }
    }

    @GetMapping("/")
    public CommonResponse<CalculationRule> findAllCalculationRule() {
        return new CommonResponse<CalculationRule>().ok().table(this.calculationRuleService.findAll());
    }

    @PutMapping("/")
    public CommonResponse<String> updateCalculationRule(@RequestBody CalculationRuleRequest ruleRequest) {
        this.calculationRuleService.updateCalculationRule(ruleRequest);
        return new CommonResponse<String>().ok();
    }

    @PostMapping("/")
    public CommonResponse<String> createCalculationRule(@RequestBody CalculationRuleRequest ruleRequest) {
        this.calculationRuleService.addCalculationRule(ruleRequest);
        return new CommonResponse<String>().ok();
    }
}
