package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.vo.CalculationRuleRequest;

import java.util.List;

public interface CalculationRuleService {

    CalculationRule findCalculationRuleById(Integer calculationRuleId);

    List<CalculationRule> findAll();

    void updateCalculationRule(CalculationRuleRequest ruleRequest);

    void addCalculationRule(CalculationRuleRequest ruleRequest);

    void deleteCalculationRule(Integer calculationRuleId);
}
