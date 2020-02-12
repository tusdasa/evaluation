package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.service.CalculationRuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationRulesController {
    private CalculationRuleService calculationRuleService;

    public CalculationRulesController(CalculationRuleService calculationRuleService) {
        this.calculationRuleService = calculationRuleService;
    }

    @GetMapping("/{id}")
    public CommonResponse<CalculationRule> findById(@PathVariable("id") Integer id) {
        CalculationRule calculationRule = calculationRuleService.findCalculationRuleById(id);
        if (calculationRule != null) {
            return new CommonResponse<CalculationRule>().ok().data(calculationRule);
        } else {
            return new CommonResponse<CalculationRule>().error("未找到");
        }
    }

    @GetMapping("/")
    public CommonResponse<CalculationRule> findAll() {
        return new CommonResponse<CalculationRule>().ok().table(this.calculationRuleService.findAll());
    }
}
