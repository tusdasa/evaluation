package net.tusdasa.evaluation.service;

import net.tusdasa.evaluation.client.CalculationClient;
import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.CalculationRule;
import net.tusdasa.evaluation.vo.CalculationRuleRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: tusdasa
 * @Date: 2020-03-09 12:49 PM
 */

@Service
public class CalculationService {

    private CalculationClient calculationClient;

    public CalculationService(CalculationClient calculationClient) {
        this.calculationClient = calculationClient;
    }


    public CommonResponse<CalculationRule> findCalculationRuleById(Integer id) {
        return this.calculationClient.findCalculationRuleById(id);
    }

    public CommonResponse<CalculationRule> findAllCalculationRule() {
        return this.calculationClient.findAllCalculationRule();
    }

    public CommonResponse<String> updateCalculationRule(CalculationRuleRequest ruleRequest) {
        return this.calculationClient.updateCalculationRule(ruleRequest);
    }

    public CommonResponse<String> createCalculationRule(CalculationRuleRequest ruleRequest) {
        return this.calculationClient.createCalculationRule(ruleRequest);
    }


}
